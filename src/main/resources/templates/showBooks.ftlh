<#import "lib/template.ftlh" as u>

<@u.base title="Books List">
    <#list books as book>
        <div class="border border-dark rounded p-4 mb-3">
            <div class="row">
                <div class="col">
                    <h4>${book.title}</h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <strong>ISBN: </strong>${book.ISBN}
                </div>
                <div class="col">
                    <strong>Author: </strong>${book.author}
                </div>
            </div>
            <div class="row mt-3">
                <div class="col">
                    <button type="button" onclick="checkBookLoaned(${book.id}, ${book.lentPersonId})" class="btn btn-primary">Check books on loan</button>
                </div>
            </div>
            <div id="feedback-${book.id}" class="m-2"></div>
            <div id="results-${book.id}" class="m-2"></div>
        </div>
    </#list>
</@u.base>

<script>
    function checkBookLoaned(bookId, personId) {
        let search = {};

        search["personId"] = personId;

        $("#click").prop("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/findPersonLoaned",
            data: JSON.stringify(search),
            dataType: 'json',
            success: function(data) {
                let personResponse = "";
                if (data.result !== null) {
                    personResponse += "<div class=\"row\">"
                    personResponse += "  <div class=\"col\">"
                    personResponse += "    <strong>Name: </strong>" + data.result.name
                    personResponse += "  </div>"
                    personResponse += "  <div class=\"col\">"
                    personResponse += "    <strong>Phone Number: </strong>" + data.result.phoneNumber
                    personResponse += "  </div>"
                    personResponse += "  <div class=\"col\">"
                    personResponse += "    <strong>Email Address: </strong>" + data.result.emailAddress
                    personResponse += "  </div>"
                    personResponse += "</div>"
                }
                console.log('SUCCESS: ', data);

                $("#feedback-"+bookId).html(data.message);
                $("#results-"+bookId).html(personResponse);
                $("#click").prop("disabled", true);
            },
            error: function (e) {
                let jsonResponse = e.responseText;
                $("#feedback-"+bookId).html(jsonResponse);
                $("#click").prop("disabled", true);
            }
        })
    }
</script>
