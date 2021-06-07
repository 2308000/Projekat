$(document).ready(function () { 
    var uloga = localStorage.getItem("role");
    if(uloga === "trener") {
        window.location.href = "trener.html";
    } else if(uloga === "clan") {
        window.location.href = "clan.html";
    } else if(uloga === "admin") {
        uloga = "admin";
    } else if(uloga === "odjavljen" || uloga == null) {
        localStorage.setItem("role", "odjavljen");
        window.location.href = "../index.html";
    } else if(uloga !== "admin") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "../index.html";
    }

    var zastita = {
        uloga
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/registracija/centarID",                 
        dataType: "json",
        contentType: "application/json",                            
        data: JSON.stringify(zastita),                                       
        success: function (res) {
            for (i = 0; i < res.length; i++) {                      
                let opcija = "<option class=\"center\">" + res[i].id + "</option>";                                
                $('#centarId').append(opcija);  
                //console.log(res);                      
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on("submit", "form", function (event) {           // kada je submit-ovana forma za kreiranje novog zaposlenog
    event.preventDefault();                                   // sprecavamo automatsko slanje zahteva da bismo pokupili podatke iz forme

    // preuzimamo vrednosti unete u formi
    var korisnickoIme = $("#UsernameField").val();
    var password = $("#PassField").val();
    var passwordC = $("#ConfirmPassField").val();
    var ime = $("#NameField").val();
    var prezime = $("#SurnameField").val();
    var email = $("#EmailField").val();
    var datumRodjenja = $("#DateField").val();
    var telefon = $("#ContactField").val();
    var zastita = localStorage.getItem("role");
    var centarID = $("#centarId").val();
    var uloga = "trener";
    var active = true;
    if(password !== passwordC) {   
        alert("Passwords do not match!");
        return false;
    } 
    if(isNaN(telefon)) {
        alert("Contact must be a telephone number!");
        return false;
    }
    // kreiramo objekat zaposlenog
    // nazivi svih atributa moraju se poklapati sa nazivima na backend-u
    var noviKorisnik = {
        korisnickoIme,
        password,
        ime,
        prezime,
        email,
        datumRodjenja,
        telefon,
        uloga,
        active,
        centarID,
        zastita
    }
    console.log(noviKorisnik);
    // ajax poziv
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/registracija/atrener",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(noviKorisnik),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            if(res.zastita == "greska") {
                alert("Morate biti administrator za ovu mogucnost!");
            } else if(res.uloga == "username") {
                alert("Korisnicko ime vec postoji!");
            } else if(res.uloga == "email") {
                alert("Email adresa vec postoji!");
            } else if(res.uloga == "broj") {
                alert("Broj telefona vec postoji!");
            } else { 
                alert("Trener " + noviKorisnik.korisnickoIme + " je uspesno kreiran!");
                window.location.href = "admin.html";
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