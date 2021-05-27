$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8080/api/termini",                 // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            
            for (i = 0; i < res.length; i++) {                      // prolazimo kroz listu svih zaposlenih
                let row = "<tr>";  
                let temp1 = res[i].pocetakTermina;
                let temp2 = res[i].krajTermina;
                temp1 = temp1.substring(0,10) + ' ' + temp1.substring(11, 16);
                temp2 = temp2.substring(0,10) + ' ' + temp2.substring(11, 16);                  
                row += "<td class=\"center\">" + temp1 + "</td>";         // ubacujemo podatke jednog zaposlenog u polja
                row += "<td class=\"center\">" + temp2 + "</td>";
                row += "<td class=\"center\">" + res[i].trajanjeTermina + "</td>";
                row += "<td class=\"center\">" + res[i].cenaTermina + "</td>";
                // kreiramo button i definisemo custom data atribut id = id zaposlenog
                //let btn = "<button class='btnSeeMore' data-id=" + res[i].id + ">Pretraga po ID-u</button>";
                //row += "<td>" + btn + "</td>";                      // ubacujemo button u poslednje polje reda                
                row += "</tr>";                                     // završavamo kreiranje reda
                $('#termini').append(row);                        // ubacujemo kreirani red u tabelu čiji je id = employees
            }
        },
        error: function (res) {                                     // ova f-ja se izvršava posle neuspešnog zahteva
            console.log("ERROR:\n", res);
        }
    });
});
