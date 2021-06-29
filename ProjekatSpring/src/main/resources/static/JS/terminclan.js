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

    var id = localStorage.getItem("id");
    var zastita = uloga;

    var provera = {
        id, 
        zastita
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/raspored/clan",                 
        dataType: "json",  
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(provera),                                              
        success: function (res) {                                   
            
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr>";  
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
                let btn1 = "<button id = \"odabir\" class='button1 newCell' data-id=" + res[i].id + ">Odaberi</button>"
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

$(document).on("submit", "form", function (event) {  
    // ajax poziv
        event.preventDefault();
        let toHide = $(".newCell");
        toHide.hide();
        let sve = false;
        let nazivTreninga  = document.forms['form-search'].naziv.value;
        let opisTreninga  = document.forms['form-search'].opis.value;
        let tipTreninga  = document.forms['form-search'].tip.value;
        let cena =  document.forms['form-search'].cena.value;
        let datum = document.forms['form-search'].datum.value;
        if(isNaN(cena) || cena === "") {
            cena = 99999;
        }
        if(datum === "") {
            datum = "2030-01-01";
        }

        let kriterijumi = {
            sve,
            nazivTreninga, 
            opisTreninga,
            tipTreninga,
            cena,
            datum,
        }

        $.ajax({
            type: "POST",                                                
            url: "http://localhost:8080/api/raspored/pretragaPoKriterijumu/clan",                 
            dataType: "json",            
            contentType: "application/json",              
            data: JSON.stringify(kriterijumi),                                           
            success: function (res) {                                
                console.log("SUCCESS:\n", res);
                for (i = 0; i < res.length; i++) {                      
                    let row = "<tr>";  
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
                    let btn1 = "<button id = \"odabir\" class='button1 newCell' data-id=" + res[i].id + ">Odaberi</button>"
                    row += "<td class=\"center\">" + btn1 + "</td>";        
                    row += "</tr>";                                     
                    $('#termini').append(row);
                    console.log(res);                        
                }
            },
            error: function (res) {                                 
                console.log("ERROR:\n", res);
            }
            
        });
    });

    $(document).on('click', '#odabir', function () {            
        console.log("kliknut");
        localStorage.setItem("id-termina", this.dataset.id);
        window.location.href = "prikaz-termina.html";    
    });

function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("termini");
  switching = true;
  //Set the sorting direction to ascending:
  dir = "asc"; 
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 2; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /*check if the two rows should switch place,
      based on the direction, asc or desc:*/
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      //Each time a switch is done, increase this count by 1:
      switchcount ++;      
    } else {
      /*If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again.*/
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

function sortTableByNumbers(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("termini");
  switching = true;
  //Set the sorting direction to ascending:
  dir = "asc"; 
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 2; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /*check if the two rows should switch place,
      based on the direction, asc or desc:*/
      if (dir == "asc") {
        if (Number(x.innerHTML) > Number(y.innerHTML)) {
            shouldSwitch = true;
            break;
          }
      } else if (dir == "desc") {
        if (Number(x.innerHTML) < Number(y.innerHTML)) {
            shouldSwitch = true;
            break;
          }
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      //Each time a switch is done, increase this count by 1:
      switchcount ++;      
    } else {
      /*If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again.*/
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}