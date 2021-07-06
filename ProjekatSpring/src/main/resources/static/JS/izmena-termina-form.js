$(document).ready(function () { 
    var uloga = localStorage.getItem("role");
    if(uloga === "admin") {
        window.location.href = "admin.html";
    } else if(uloga === "clan") {
        window.location.href = "clan.html";
    } else if(uloga === "trener") {
        uloga = "trener";
    } else if(uloga === "odjavljen" || uloga == null) {
        localStorage.setItem("role", "odjavljen");
        window.location.href = "../index.html";
    } else if(uloga !== "trener") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "../index.html";
    }
    var zastita = uloga;
    var id = localStorage.getItem("id");
    var provera = {
        zastita,
        id
    }
    var x = localStorage.getItem("termin-id");
    if(x == null) {
        localStorage.setItem("termin-id", 0);
        window.location.href = "izmena-termina.html"
    }
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/raspored/getSale",                
        dataType: "json",
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                       
        success: function (res) {
            for (i = 0; i < res.length; i++) {                      
                let opcijaSala = "<option class=\"center\">" + res[i].salaID + "</option>";     
                $('#sala').append(opcijaSala);  
                //console.log(res);                      
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on("submit", "form", function (event) {           // kada je submit-ovana forma za kreiranje novog zaposlenog
    event.preventDefault();                                     // sprecavamo automatsko slanje zahteva da bismo pokupili podatke iz forme
    var uloga = localStorage.getItem("role");
    if(uloga === "admin") {
        //console.log("Morate se izlogovati da biste se ponovo ulogovali!");
        window.location.href = "admin.html"
    } else if(uloga === "clan") {
        //console.log("Morate se izlogovati da biste se ponovo ulogovali!");
        window.location.href = "clan.html";
    } else if (uloga === "odjavljen" || uloga == null) {
        localStorage.setItem("role", "odjavljen");
        window.location.href = "../index.html";
    } else if(uloga !== "trener") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "../index.html";
    }

    var zastita = uloga;

    var pocetakTermina = $("#pocetak").val();
    var krajTermina = $("#kraj").val();
    /*console.log(pocetakTermina);
    console.log(krajTermina);*/
    var datum1 = pocetakTermina.substring(0, 10);
    var datum2 = krajTermina.substring(0, 10);
    /*console.log(datum1);
    console.log(datum2);*/
    if(datum1 !== datum2) {
        alert("Trening se mora završavati isti dan kada i počne!");
        return false;
    }
    var sati1 = pocetakTermina.substring(11, 13);
    var minute1 = pocetakTermina.substring(14, 16);
    var sati2 = krajTermina.substring(11, 13);
    var minute2 = krajTermina.substring(14, 16);
    /*console.log(sati1);
    console.log(minute1);
    console.log(sati2);
    console.log(minute2);
    var trajanje = (Number(sati2) * 60 + Number(minute2)) - (Number(sati1) * 60 + Number(minute1));
    console.log(trajanje);*/
    var trajanjeTermina = (Number(sati2) * 60 + Number(minute2)) - (Number(sati1) * 60 + Number(minute1));
    var cenaTermina = $("#cena").val();
    var nazivTreninga = $("#nazivTreninga").val();
    var salaID = $("#sala").val();
    var trenerID = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");
    var id = localStorage.getItem("id-termina");
    console.log(trenerID);
    
    if(isNaN(cenaTermina)) {
        alert("Cena mora biti broj!");
        return false;
    }
    // kreiramo objekat zaposlenog
    // nazivi svih atributa moraju se poklapati sa nazivima na backend-u
    var izmenjenTermin = {
        pocetakTermina,
        krajTermina,
        trajanjeTermina,
        cenaTermina,
        nazivTreninga,
        salaID,
        trenerID,
        zastita,
        id
    }
    console.log(izmenjenTermin);
    // ajax poziv
    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/raspored/izmeni",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(izmenjenTermin),                          // u body-ju šaljemo novog zaposlenog (JSON.stringify() pretvara JavaScript objekat u JSON)
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            if(res.zastita == "greska") {
                alert("Morate biti trener za ovu mogucnost!");
            } else if(res.zastita == "pocetak") {
                alert("Pocetak termina upada u drugi termin");
            } else if(res.zastita == "kraj") {
                alert("Kraj termina upada u drugi termin");
            } else if(res.zastita == "oba") { 
                alert("Termin preklapa drugi termin");
            } else { 
                alert("Termin je uspesno izmenjen!");
                window.location.href = "termin.html";
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