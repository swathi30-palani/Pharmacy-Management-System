// ==========================================
// 1. FORM VALIDATION + EVENT HANDLING
// Registration Form
// ==========================================

let registerForm = document.getElementById("registerForm");

if (registerForm) {

    registerForm.addEventListener("submit", function(event) {

        event.preventDefault();

        try {

            let name = document.getElementById("name").value;
            let email = document.getElementById("email").value;
            let phone = document.getElementById("phone").value;
            let password = document.getElementById("password").value;

            // Form Validation
            if (name.length < 3) {
                throw new Error("Name must contain at least 3 characters.");
            }

            if (phone.length !== 10) {
                throw new Error("Phone number must contain 10 digits.");
            }

            if (password.length < 6) {
                throw new Error("Password must contain at least 6 characters.");
            }

            alert("Registration successful!");

            registerForm.reset();

        } catch (error) {

            // Exception Handling
            alert(error.message);

        }

    });
}


// ==========================================
// 2. LOGIN FORM
// ==========================================

let loginForm = document.getElementById("loginForm");

if (loginForm) {

    loginForm.addEventListener("submit", function(event) {

        event.preventDefault();

        try {

            let email =
                document.getElementById("loginEmail").value;

            let password =
                document.getElementById("loginPassword").value;

            if (email === "" || password === "") {
                throw new Error("Please enter email and password.");
            }

            alert("Login successful!");

        } catch (error) {

            alert(error.message);

        }

    });
}


// ==========================================
// 3. DOM MANIPULATION
// Add Medicine
// ==========================================

let medicineForm =
    document.getElementById("medicineForm");

if (medicineForm) {

    medicineForm.addEventListener("submit", function(event) {

        event.preventDefault();

        try {

            let name =
                document.getElementById("medicineName").value;

            let category =
                document.getElementById("category").value;

            let price =
                document.getElementById("price").value;

            let quantity =
                document.getElementById("quantity").value;

            if (name === "" || category === "") {
                throw new Error("Please enter all medicine details.");
            }

            // DOM Manipulation
            let table =
                document.getElementById("medicineTable");

            let row = table.insertRow();

            row.innerHTML =
                "<td>" + name + "</td>" +
                "<td>" + category + "</td>" +
                "<td>₹" + price + "</td>" +
                "<td>" + quantity + "</td>";

            alert("Medicine added successfully!");

            medicineForm.reset();

        } catch (error) {

            alert(error.message);

        }

    });
}


// ==========================================
// 4. EVENT HANDLING + DOM MANIPULATION
// Medicine Search
// ==========================================

let search = document.getElementById("search");

if (search) {

    search.addEventListener("keyup", function() {

        let value = search.value.toLowerCase();

        let rows =
            document.querySelectorAll("#medicineTable tbody tr");

        rows.forEach(function(row) {

            let medicineName =
                row.cells[0].textContent.toLowerCase();

            if (medicineName.includes(value)) {

                row.style.display = "";

            } else {

                row.style.display = "none";

            }

        });

    });

}


// ==========================================
// 5. CONTACT FORM
// EVENT HANDLING + VALIDATION + TRY-CATCH
// ==========================================

let contactForm =
    document.getElementById("contactForm");

if (contactForm) {

    contactForm.addEventListener("submit", function(event) {

        event.preventDefault();

        try {

            let name =
                document.getElementById("contactName").value;

            let email =
                document.getElementById("contactEmail").value;

            let message =
                document.getElementById("message").value;

            if (name === "" ||
                email === "" ||
                message === "") {

                throw new Error("Please fill all fields.");

            }

            alert("Message sent successfully!");

            contactForm.reset();

        } catch (error) {

            alert(error.message);

        }

    });
}


// ==========================================
// 6. JSON REPRESENTATION OF SAMPLE DATA
// ==========================================

let medicines = [
    {
        "id": 1,
        "name": "Paracetamol",
        "category": "Pain Relief",
        "price": 25,
        "quantity": 100
    },
    {
        "id": 2,
        "name": "Cetirizine",
        "category": "Allergy",
        "price": 30,
        "quantity": 50
    },
    {
        "id": 3,
        "name": "Vitamin C",
        "category": "Vitamin",
        "price": 40,
        "quantity": 75
    }
];

console.log(medicines);