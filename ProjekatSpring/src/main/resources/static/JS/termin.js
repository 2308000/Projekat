$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    $.ajax({
        type: "GET",                                              
        url: "http://localhost:8080/api/termini",                 
        dataType: "json",                                          
        success: function (res) {                                   
            
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr>";  
                let temp1 = res[i].pocetakTermina;
                let temp2 = res[i].krajTermina;
                temp1 = temp1.substring(0,10) + ' ' + temp1.substring(11, 16);
                temp2 = temp2.substring(0,10) + ' ' + temp2.substring(11, 16);                  
                row += "<td class=\"newCell center\">" + temp1 + "</td>";         
                row += "<td class=\"newCell center\">" + temp2 + "</td>";
                row += "<td class=\"newCell center\">" + res[i].trajanjeTermina + "</td>";
                row += "<td class=\"newCell center\">" + res[i].cenaTermina + "</td>";                                 
                row += "</tr>";                                     
                $('#termini').append(row);                        
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
        let cena =  document.forms['form-search'].cena.value;
        let trajanje = document.forms['form-search'].trajanje.value;

        if(isNaN(cena) || cena === "") {
            cena = 99999;
        }
        if(isNaN(trajanje) || trajanje === "") {
            trajanje = 99999;
        }

        let kriterijumi = {
            sve,
            cena,
            trajanje,
        }

        $.ajax({
            type: "POST",                                                
            url: "http://localhost:8080/api/termini/pretragaPoKriterijumu",                 
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
                    row += "<td class=\"newCell center\">" + temp1 + "</td>";         
                    row += "<td class=\"newCell center\">" + temp2 + "</td>";
                    row += "<td class=\"newCell center\">" + res[i].trajanjeTermina + "</td>";
                    row += "<td class=\"newCell center\">" + res[i].cenaTermina + "</td>";           
                    row += "</tr>";                                     
                    $('#termini').append(row);                        
                }
            },
            error: function (res) {                                 
                console.log("ERROR:\n", res);
            }
            
        });
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