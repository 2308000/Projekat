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

    var zastita = uloga;
    var provera = {
        zastita
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/registracija/centarID",                 
        dataType: "json",
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                       
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
    // preuzimamo vrednosti unete u formi
    var kapacitet = $("#CapacityField").val();
    var oznakaSale = $("#OznakaField").val();
    var centarID = $("#centarId").val();
    // kreiramo objekat zaposlenog
    // nazivi svih atributa moraju se poklapati sa nazivima na backend-u
    var novaSala = {
        kapacitet,
        oznakaSale,
        centarID,
        zastita
    }
    console.log(novaSala);
    // ajax poziv
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/sala/dodavanje",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(novaSala),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            if(res.zastita == "oznaka") {
                alert("Ova oznaka sale vec postoji!");
            } else if(res.zastita == "greska") {
                alert("Morate biti administrator da biste dodali salu!");
            } else {
                alert("Sala " + novaSala.oznakaSale + " je dodana u centar " + novaSala.centarID + "!");
                window.location.href = "admin.html";
            }
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška!");
        }
    });

});