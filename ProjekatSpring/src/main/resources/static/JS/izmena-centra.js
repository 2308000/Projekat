$(document).on("submit", "form", function (event) {           // kada je submit-ovana forma za kreiranje novog zaposlenog
    event.preventDefault();                                     // sprecavamo automatsko slanje zahteva da bismo pokupili podatke iz forme
    var uloga = localStorage.getItem("role");
    if(uloga === "trener") {
        //console.log("Morate se izlogovati da biste se ponovo ulogovali!");
        window.location.href = "trener.html"
    } else if(uloga === "clan") {
        //console.log("Morate se izlogovati da biste se ponovo ulogovali!");
        window.location.href = "clan.html";
    } else if (uloga === "odjavljen" || uloga == null) {
        localStorage.setItem("role", "odjavljen");
        window.location.href = "../index.html";
    } else if(uloga !== "admin") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "../index.html";
    }

    var zastita = uloga;

    var id = localStorage.getItem("id-centra");
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
    var izmenjenCentar = {
        id,
        nazivCentra,
        adresa,
        brojTelefonaCentra,
        emailCentra,
        zastita
    }
    console.log(izmenjenCentar);
    // ajax poziv
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/fitnessCentar/izmeni",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(izmenjenCentar),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            if(res.zastita == "broj") {
                alert("Broj fitness centra vec postoji!");
            } else if(res.zastita == "email") {
                alert("Email centra vec postoji!");
            } else if(res.zastita == "greska") {
                alert("Morate biti administrator da vriste izmene!");
            } else {
                alert("Centar " + izmenjenCentar.nazivCentra + " je uspesno izmenjen!");
                window.location.href = "centar.html";
            }
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška!");
        }
    });
});

function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", "odjavljen");
}