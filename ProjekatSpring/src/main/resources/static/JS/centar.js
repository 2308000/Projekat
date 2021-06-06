$(document).ready(function () { 
    var uloga = localStorage.getItem("role");
    if(uloga === "odjavljen" || uloga === null) {
        window.location.href = "../index.html";
    }
    if(uloga === "trener") {
        window.location.href = "trener.html";
    }
    if(uloga === "clan") {
        window.location.href = "clan.html";
    }

    let zastita = uloga;
    var provera = {
        zastita
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/fitnessCentar/prikaz",                 
        dataType: "json",
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(provera),                                          
        success: function (res) {
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr id = 'tempRed" + res[i].id +"'>";
                row += "<td class=\"newCell center\">" + res[i].id + "</td>";
                row += "<td class=\"newCell center\" data-naziv=" + res[i].nazivCentra + ">" + res[i].nazivCentra + "</td>";
                row += "<td class=\"newCell center\" data-adresa=" + res[i].adresa + ">" + res[i].adresa + "</td>";
                row += "<td class=\"newCell center\" data-broj=" + res[i].brojTelefonaCentra + ">" + res[i].brojTelefonaCentra + "</td>"; 
                row += "<td class=\"newCell center\" data-email=" + res[i].emailCentra + ">" + res[i].emailCentra + "</td>";    
                let btn1 = "<button id = \"izmena\" class='button1' data-id=" + res[i].id + ">Izmeni</button>"
                let btn2 = "<button id = \"brisanje\" class='button1' data-id=" + res[i].id + ">Obriši</button>"
                row += "<td class=\"center\">" + btn1 + "</td>";   
                row += "<td class=\"center\">" + btn2 + "</td>";                           
                row += "</tr>";                                     
                $('#centri').append(row);  
                //console.log(res);  
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#izmena', function () {
    localStorage.setItem("id", this.dataset.id);
    window.location.href = "izmena-centra.html"

});

$(document).on('click', '#brisanje', function () {            // kada je button (čija je klasa class = btnSeeMore) kliknut
    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    let id = this.dataset.id;

    let toHide = "#tempRed";
    toHide += id;
    let tableRow = $(toHide);
    tableRow.hide();

    // nakon što korisnik klikne dugme See More dobavljaju se i prikazuju podaci o traženom zaposlenom
    $.ajax({
        type: "PUT",                                              
        url: "http://localhost:8080/api/fitnessCentar/brisanje/" + id,                 
        dataType: "json",                                  // tip povratne vrednosti
        contentType: "application/json",
        //data: JSON.stringify(trenerId),  
        success: function (res) {                               // ova f-ja se izvršava posle uspešnog zahteva
            console.log("SUCCESS:\n", res);
            alert("Centar " + id + " je obrisan!");
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