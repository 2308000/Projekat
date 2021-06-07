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

    var id = localStorage.getItem("id");
    var kapacitet = $("#CapacityField").val();
    var oznakaSale = $("#OznakaField").val();
    // kreiramo objekat zaposlenog
    // nazivi svih atributa moraju se poklapati sa nazivima na backend-u
    var izmenjenaSala = {
        id,
        kapacitet,
        oznakaSale,
        zastita
    }
    console.log(izmenjenaSala);
    // ajax poziv
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/sala/izmeni",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(izmenjenaSala),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            if(res.zastita == "oznaka") {
                alert("Ova oznaka sale vec postoji!");
            } else if(res.zastita == "greska") {
                alert("Morate biti administrator za biste mogli da vrsite izmene!");
            } else {
                alert("Centar " + izmenjenaSala.oznakaSale + " je uspesno izmenjena!");
                window.location.href = "sala.html";
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