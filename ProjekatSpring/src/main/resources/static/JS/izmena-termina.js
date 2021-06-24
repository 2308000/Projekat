$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
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

    var id = localStorage.getItem("id");
    var zastita = uloga;

    var provera = {
        id, 
        zastita
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/raspored/trener",                 
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
                let btn1 = "<button id = \"izmena\" class='button1' data-id=" + res[i].id + ">Izmeni</button>"
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

$(document).on('click', '#izmena', function () {
    localStorage.setItem("id-termina", this.dataset.id);
    window.location.href = "izmena-termina-form.html"

});

function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", "odjavljen");
}