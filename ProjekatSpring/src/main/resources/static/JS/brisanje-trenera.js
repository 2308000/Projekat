$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    
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
    var zastita = {
        uloga
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/verifikacija/brisanje",                 
        dataType: "json",
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(zastita),                                          
        success: function (res) {
            for (i = 0; i < res.length; i++) {                      
                let row = "<tr id = 'tempRed" + res[i].id +"'>";
                row += "<td class=\"newCell center\">" + res[i].id + "</td>";
                row += "<td class=\"newCell center\">" + res[i].korisnickoIme + "</td>";
                row += "<td class=\"newCell center\">" + res[i].ime + "</td>";
                row += "<td class=\"newCell center\">" + res[i].prezime + "</td>";
                row += "<td class=\"newCell center\">" + res[i].centarID + "</td>";
                row += "<td class=\"newCell center\">" + res[i].active + "</td>";
                let btn1 = "<button id = \"delete\" class='button1' data-id=" + res[i].id + ">Delete</button>";
                row += "<td class=\"center\">" + btn1 + "</td>";                          
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

$(document).on('click', '#delete', function () {            
    let trenerId = this.dataset.id;

    let toHide = "#tempRed";
    toHide += trenerId;
    let tableRow = $(toHide);
    tableRow.hide();

    console.log(trenerId);
    $.ajax({
        type: "PUT",                                              
        url: "http://localhost:8080/api/verifikacija/brisanje/" + trenerId,                 
        dataType: "json",
        contentType: "application/json",     
        success: function (res) {                               
            console.log("SUCCESS:\n", res);
            alert("Trener je obrisan!");
        },
        error: function (res) {                                
            console.log("ERROR:\n", res);
        }
    });
});

function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", "odjavljen");
}