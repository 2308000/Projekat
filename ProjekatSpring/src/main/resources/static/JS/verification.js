$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    $.ajax({
        type: "GET",                                              
        url: "http://localhost:8080/api/verifikacija/inactive",                 
        dataType: "json",                                          
        success: function (res) {
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr id = 'tempRed" + res[i].id +"'>";
                row += "<td class=\"newCell center\">" + res[i].id + "</td>";
                row += "<td class=\"newCell center\">" + res[i].korisnickoIme + "</td>";
                row += "<td class=\"newCell center\">" + res[i].ime + "</td>";
                row += "<td class=\"newCell center\">" + res[i].prezime + "</td>";
                let btn1 = "<button id = \"accept\" class='button1' data-id=" + res[i].id + ">Accept</button>";
                row += "<td class=\"center\">" + btn1 + "</td>";
                let btn2 = "<button id = \"decline\" class='button1' data-id=" + res[i].id + ">Decline</button>";   
                row += "<td class=\"center\">" + btn2 + "</td>";                           
                row += "</tr>";                                     
                $('#treneri').append(row);  
                //console.log(res);                      
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#accept', function () {            // kada je button (čija je klasa class = btnSeeMore) kliknut
    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    let trenerId = this.dataset.id;
    let toHide = "#tempRed";
    toHide += trenerId;
    let tableRow = $(toHide);
    tableRow.hide();

    // nakon što korisnik klikne dugme See More dobavljaju se i prikazuju podaci o traženom zaposlenom
    console.log(trenerId);
    $.ajax({
        type: "PUT",                                              
        url: "http://localhost:8080/api/verifikacija/prihvacen/" + trenerId,                 
        dataType: "json",
        contentType: "application/json",     
        //data: JSON.stringify(trenerId),  
        success: function (res) {                               // ova f-ja se izvršava posle uspešnog zahteva
            console.log("SUCCESS:\n", res);
            alert(res.korisnickoIme + " je prihvacen!");
        },
        error: function (res) {                                // ova f-ja se izvršava posle neuspešnog zahteva
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#decline', function () {            // kada je button (čija je klasa class = btnSeeMore) kliknut
    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    let trenerId = this.dataset.id;

    let toHide = "#tempRed";
    toHide += trenerId;
    let tableRow = $(toHide);
    tableRow.hide();

    // nakon što korisnik klikne dugme See More dobavljaju se i prikazuju podaci o traženom zaposlenom
    $.ajax({
        type: "DELETE",                                              
        url: "http://localhost:8080/api/verifikacija/odbijen/" + trenerId,                 
        dataType: "json",                                  // tip povratne vrednosti
        contentType: "application/json",
        //data: JSON.stringify(trenerId),  
        success: function (res) {                               // ova f-ja se izvršava posle uspešnog zahteva
            console.log("SUCCESS:\n", res);
            alert("Zahtev je odbijen!");
        },
        error: function (res) {                                // ova f-ja se izvršava posle neuspešnog zahteva
            console.log("ERROR:\n", res);
        }
    });
});