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
            alert("Centar " + noviCentar.nazivCentra + " je uspesno kreiran!");
            window.location.href = "admin.html";
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška!");
        }
    });
    
});

/*function test() {
    console.log("hello");
}*/