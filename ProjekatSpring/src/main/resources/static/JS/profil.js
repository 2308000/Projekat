function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", "odjavljen");
}


$(document).on('click', '.button', function () {            // kada je button (čija je klasa class = btnSeeMore) kliknut
    let toHide = $(".card");                      // dobavi element čiji je id = allEmployees  (pogledati html)
    toHide.hide();                                        // sakrij taj element

    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    /*let clanId = this.dataset.id;

    // nakon što korisnik klikne dugme See More dobavljaju se i prikazuju podaci o traženom zaposlenom
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clan/" + clanId,
        dataType: "json",
        success: function (res) {                               // ova f-ja se izvršava posle uspešnog zahteva
            console.log("SUCCESS:\n", res);
            $('#firstName').append(res.firstName);
            $('#lastName').append(res.lastName);
            $('#position').append(res.position);
            let employeeDiv = $("#oneEmployee");               // dobavi element čiji je id = oneEmployee
            employeeDiv.show();                                // prikaži taj element
        },
        error: function (res) {                                // ova f-ja se izvršava posle neuspešnog zahteva
            console.log("ERROR:\n", res);
        }
    });*/
});