<#macro base title>
    <htlm>
        <head>
            <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet">
            <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
            <script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
            <title>${title}</title>
        </head>
    </htlm>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Library</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/books">Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/people">People</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="jumbotron mt-1">
        <div class="container">
            <h1>${title}</h1>
        </div>
    </div>
    <div class="container">
        <#nested>
    </div>
    </body>
</#macro>