<#import "lib/template.ftlh" as u>

<@u.base title="Welcome to the Library">
    <div class="row">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Check out list of books</h5>
                    <p class="card-text">Go here if you want to see info on the books in this library</p>
                    <a href="/books" class="btn btn-primary">Books</a>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Check out list of people</h5>
                    <p class="card-text">Go here if you want to see info on the library's users</p>
                    <a href="/people" class="btn btn-primary">People</a>
                </div>
            </div>
        </div>
    </div>
</@u.base>
