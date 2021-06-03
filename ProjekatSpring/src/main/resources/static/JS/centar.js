$(document).ready(function () { 
    var uloga = localStorage.getItem("role");
    if(uloga === null) {
        window.location.href = "index.html";
    }
    if(uloga === "trener") {
        window.location.href = "trener.html";
    }
    if(uloga === "clan") {
        window.location.href = "clan.html";
    }
});

$(document).on("submit", "form", function (event) {           // kada je submit-ovana forma za kreiranje novog zaposlenog
    event.preventDefault();                                   // sprecavamo automatsko slanje zahteva da bismo pokupili podatke iz forme

    // preuzimamo vrednosti unete u formi
    var nazivCentra = $("#NameField").val();
    var adresa = $("#AdressField").val();
    var brojTelefonaCentra = $("#ContactField").val();
    var emailCentra = $("#EmailField").val();
    var zastita = localStorage.getItem("role");
    if(isNaN(brojTelefonaCentra)) {
        alert("Contact must be a telephone number!");
        return false;
    }
    // kreiramo objekat zaposlenog
    // nazivi svih atributa moraju se poklapati sa nazivima na backend-u
    var noviCentar = {
        nazivCentra,
        adresa,
        brojTelefonaCentra,
        emailCentra,
        zastita
    }
    console.log(noviCentar);
    // ajax poziv
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/fitnessCentar/novi",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(noviCentar),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            if(res.zastita == "admin") {
                alert("Centar " + noviCentar.nazivCentra + " je uspesno kreiran!");
                window.location.href = "admin.html";
            } else if(res.zastita == "broj") {
                alert("Broj vec postoji!");
            } else if(res.zastita == "email") {
                alert("Email adresa vec postoji!");
            } else {
                alert("Nemate privilegiju dodavanja centra!");
                if(res.zastita == "clan") {
                    window.location.href = "clan.html";
                }
                if(res.zastita == "trenre") {
                    window.location.href = "trener.html";
                }
                window.location.href = "../index.html";
            }
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška!");
        }
    });
    
});

/*function test() {
    console.log("hello");
}*/