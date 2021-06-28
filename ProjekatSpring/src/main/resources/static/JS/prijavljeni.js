$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    var uloga = localStorage.getItem("role");
    if(uloga === "admin") {
        window.location.href = "admin.html";
    } else if(uloga === "trener") {
        window.location.href = "trener.html";
    } else if(uloga === "clan") {
        uloga = "clan";
    } else if(uloga === "odjavljen" || uloga == null) {
        localStorage.setItem("role", "odjavljen");
        window.location.href = "../index.html";
    } else if(uloga !== "clan") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "../index.html";
    }

    var clanID = localStorage.getItem("id");
    console.log("ID clana je " + clanID);
    var zastita = uloga;

    var provera = {
        clanID, 
        zastita
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/prikazPrijavljenih",                 
        dataType: "json",  
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(provera),                                              
        success: function (res) {                                   
            
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr id = 'tempRed" + res[i].id +"'>";
                let temp1 = res[i].pocetakTermina;
                let temp2 = res[i].krajTermina;
                temp1 = temp1.substring(0,10) + ' ' + temp1.substring(11, 16);
                temp2 = temp2.substring(0,10) + ' ' + temp2.substring(11, 16);
                row += "<td class=\"newCell center\">" + res[i].nazivTreninga + "</td>";
                row += "<td class=\"newCell center\">" + res[i].opisTreninga + "</td>";
                row += "<td class=\"newCell center\">" + res[i].tipTreninga + "</td>";                  
                row += "<td class=\"newCell center\">" + temp1 + "</td>";         
                row += "<td class=\"newCell center\">" + temp2 + "</td>";
                row += "<td class=\"newCell center\">" + res[i].trajanjeTermina + "</td>";
                row += "<td class=\"newCell center\">" + res[i].cenaTermina + "</td>"; 
                let btn1 = "<button id = \"odjava\" class='button1' data-id=" + res[i].id + ">Odjavi se</button>"
                row += "<td class=\"center\">" + btn1 + "</td>";                                
                row += "</tr>";                                     
                $('#termini').append(row);  
                //console.log(res);                      
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#odjava', function () {            // kada je button (čija je klasa class = btnSeeMore) kliknut
    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    var id = this.dataset.id;
    let toHide = "#tempRed";
    toHide += id;
    let tableRow = $(toHide);
    tableRow.hide();
    var clanID = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");
    var odjavljenTermin = {
        id,
        clanID,
        zastita
    }
    // nakon što korisnik klikne dugme See More dobavljaju se i prikazuju podaci o traženom zaposlenom
    console.log(odjavljenTermin);
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/odjavaTermina",                 
        dataType: "json",
        contentType: "application/json",   
        data: JSON.stringify(odjavljenTermin),   
        //data: JSON.stringify(trenerId),  
        success: function (res) {                               // ova f-ja se izvršava posle uspešnog zahteva
            console.log("SUCCESS:\n", res);
            if(res.zastita === "denied") {
                alert("Morate biti član da biste odjavili termin!");
            } else if(res.zastita === "ok") {
                alert("Termin uspješno odjavljen!");
            } else {
                alert("Odjava neuspješna!");
            }
        },
        error: function (res) {                                // ova f-ja se izvršava posle neuspešnog zahteva
            console.log("ERROR:\n", res);
        }
    });
});

function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", "odjavljen");
}