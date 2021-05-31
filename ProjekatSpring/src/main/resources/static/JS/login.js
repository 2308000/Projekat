$(document).ready(function () { 
    var uloga = localStorage.getItem("role");
    if(uloga === "admin") {
        console.log("Morate se izlogovati da biste se ponovo ulogovali!");
        window.location.href = "admin.html";
    }
    if(uloga === "trener") {
        console.log("Morate se izlogovati da biste se ponovo ulogovali!");
        window.location.href = "trener.html";
    }
    if(uloga === "clan") {
        console.log("Morate se izlogovati da biste se ponovo ulogovali!");
        window.location.href = "clan.html";
    }
});

$(document).on("submit", "form", function (event) {           // kada je submit-ovana forma za kreiranje novog zaposlenog
    event.preventDefault();                                   // sprecavamo automatsko slanje zahteva da bismo pokupili podatke iz forme

    // preuzimamo vrednosti unete u formi
    var korisnickoIme = $("#UsernameField").val();
    var password = $("#PassField").val();
    // kreiramo objekat zaposlenog
    // nazivi svih atributa moraju se poklapati sa nazivima na backend-u
    var Login = {
        korisnickoIme,
        password,
    }
    console.log(Login);
    // ajax poziv
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/login",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(Login),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            alert(res.error);
            if(res.uloga === "admin") {
                localStorage.setItem("role", "admin");
                window.location.href = "admin.html";
            }
            if(res.uloga === "trener") {
                localStorage.setItem("role", "trener");
                window.location.href = "trener.html";
            }
            if(res.uloga === "clan") {
                localStorage.setItem("role", "clan");
                window.location.href = "clan.html";
            }
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška!");
        }
    });
    
});