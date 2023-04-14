

function generateParagraph() {
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let country = document.getElementById("country").value;
    let age = document.getElementById("age").value;
    let semester = document.getElementById("semester").value;
    let institution = document.getElementById("institution").value;
    let email = document.getElementById("email").value;

    var paragraph = "I am " + firstName + " " + lastName + " from " + country + " currently studing in " + semester + "th semester in " + 
        institution + ".\nI am " + age + " years old.\nMy email is " + email + ".";
    
    
    
    
    
    
    console.log(para);
    document.getElementById("generatedParagraph").innerHTML = paragraph;

}

