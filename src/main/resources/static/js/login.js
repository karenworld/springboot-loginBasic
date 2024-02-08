function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    
    $.ajax({
        type: "POST",
        url: "/api/login",
        data: { username: username, password: password },
        success: function(response) {
            // Check if the response indicates a successful login
            if (response.startsWith("Welcome")) {
                // Extract the username and role from the response message
                var messageParts = response.split(":");
                var username = messageParts[1].trim();
                var role = messageParts[2].trim();
                
                // Redirect to the welcome page with username and role parameters
                window.location.href = "/welcome?username=" + username + "&role=" + role;
            } else {
                // If login is not successful, display the response message as an error
               window.location.href = "/error";
             //   $("#errorMessage").text(response);
            }
        },
        error: function(xhr, status, error) {
            // If login fails, display error message
            window.location.href = "/error";
            //$("#errorMessage").text(xhr.responseText);
        }
    });
}

