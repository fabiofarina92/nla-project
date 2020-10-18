<#import "lib/template.ftlh" as u>

<@u.base title="List of People">
    <#list people as person>
        <div class="border p-4 mb-3">
            <div class="row">
                <div class="col">
                    <h4>${person.name}</h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <strong>ID: </strong>${person.id}
                </div>
                <div class="col">
                    <strong>Phone Number: </strong>${person.phoneNumber}
                </div>
                <div class="col">
                    <strong>Email: </strong>${person.emailAddress}
                </div>
            </div>
            <div class="row mt-3">
                <div class="col">
                    <button type="button" onclick="checkBookLoaned(${person.id})" class="btn btn-primary">Check books on loan</button>
                </div>
            </div>
            <div id="feedback-${person.id}" class="m-2"></div>
            <div id="results-${person.id}" class="m-2"></div>
        </div>
    </#list>
</@u.base>

<script>
    function checkBookLoaned(personId) {
        let search = {};

        search["personId"] = personId;

        $("#click").prop("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/findOnLoan",
            data: JSON.stringify(search),
            dataType: 'json',
            success: function(data) {
                let tableResponse = "";
                if (data.results.length > 0) {
                    tableResponse += "<table class=\"table\">";
                    tableResponse += "<thead>";
                    tableResponse += "  <tr>";
                    tableResponse += "    <th scope=\"col\">ISBN</th>";
                    tableResponse += "    <th scope=\"col\">Title</th>";
                    tableResponse += "    <th scope=\"col\">Author</th>";
                    tableResponse += "  <tr>";
                    tableResponse += "</thead>";
                    tableResponse += "<tbody>";

                    for (let result of data.results) {
                        tableResponse += "<tr>";
                        tableResponse += "<th scope=\"row\">" + result.isbn + "</th>" ;
                        tableResponse += "<td>" + result.title + "</td>" ;
                        tableResponse += "<td>" + result.author + "</td>" ;
                        tableResponse += "</tr>";
                    }
                    tableResponse += "</tbody>";
                    tableResponse += "</table>";
                }

                $("#feedback-"+personId).html(data.message);
                $("#results-"+personId).html(tableResponse);
                $("#click").prop("disabled", true);
            },
            error: function (e) {
                let jsonResponse = e.responseText;
                $("#feedback-"+personId).html(jsonResponse);
                $("#click").prop("disabled", true);
            }
        })
    }
</script>
