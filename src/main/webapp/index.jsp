<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>File Upload Example in JSP and Servlet - Java web application</title>

    </head>



    <body>

        <div>

            <h3> Escolha um arquivo de até 1MB para subir para o Servidor </h3>


            <form action="upload" method="post" enctype="multipart/form-data">

                <input type="file" name="file" id="i_file" required />

                <input type="submit" value="Enviar" id="i_submit"  />
                <br>
                
                <input type ="text" style="border: none; color: red; width: 100%" id="filestatus" readonly>

            </form>     

            <a href="baixar.jsp">Baixar</a>

        </div>



    </body>
    <script src="js/jquery.js"></script>

    <script>
        $('#i_submit').click(function () {
            //check whether browser fully supports all File API
            if (window.File && window.FileReader && window.FileList && window.Blob)
            {
                //get the file size and file type from file input field
                var fsize = $('#i_file')[0].files[0].size;

                if (fsize > 1048576) //do something if file size more than 1 mb (1048576)
                {
                    //alert(fsize + " bites\nArquivo muito grande!");
                    document.getElementById('filestatus').value = "Arquivo com "+fsize+" bytes é muito grande";
                    return false;
                } else {
                    alert(fsize + " bites\nArquivo com tamanho aceito!");
                }
            } else {
                alert("Favor atualizar seu navegador, pois seu navegador atual não possui alguns atributos necessários!");
            }
        });

    </script>
</html>
