    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>

    <body>
        <h1>Ajouter une Unité d'Oeuvre</h1>

        <form action="ajouter" method="post">
            <label for="libele">Libellé:</label><br>
            <input type="text" id="libele" name="libele" required><br><br>

            <input type="submit" value="Ajouter">
        </form>
        <h1>Message: ${erreur}</h1>
    </body>

    </html>