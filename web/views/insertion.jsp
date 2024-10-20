<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,source.model.unite.*,source.model.centre.*, source.model.charge.* ,source.model.centreCharge.*" %>
<%
    List<UniteOeuvre> uniteOeuvre = (List<UniteOeuvre>)request.getAttribute("listeUniteOeuvre");
    List<Centre> centres = (List<Centre>)request.getAttribute("listeCentre");
    Charge charge = (Charge) request.getAttribute("charge");
    List<CentreCharge> centreCharge = (List<CentreCharge>)request.getAttribute("listeCentreCharge");
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Far'in</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/feather/feather.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->

  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/vertical-layout-light/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/vaovao.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo.png" />


  
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/assets/images/logo_long.png" class="mr-2" alt="logo"/></a>
        <a class="navbar-brand brand-logo-mini" href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="logo"/></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="icon-menu"></span>
        </button>
        <ul class="navbar-nav navbar-nav-right"></ul>
      </div>
    </nav>


    <!-- partial -->
    <div class="container-fluid page-body-wrapper">


      <!-- Paramètre bas droite  -->
      <!-- partial:partials/_settings-panel.html -->
      <div class="theme-setting-wrapper">
        <div id="settings-trigger"><i class="ti-settings"></i></div>
        <div id="theme-settings" class="settings-panel">
          <i class="settings-close ti-close"></i>
          <p class="settings-heading">Mode</p>
          <div class="sidebar-bg-options selected" id="sidebar-light-theme"><div class="img-ss rounded-circle bg-light border mr-3"></div>Sombre</div>
          <div class="sidebar-bg-options" id="sidebar-dark-theme"><div class="img-ss rounded-circle bg-dark border mr-3"></div>Clair</div>
        </div>
      </div>

<!-- MENU SIDEBAR ------------------------------------------------------------------------------------------------------>
      <!-- partial -->
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="changeDash">
              <i class="icon-grid menu-icon"></i>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}">
              <i class="icon-grid menu-icon"></i>
              <span class="menu-title">Tableau</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="inserCharge">
              <i class="icon-paper menu-icon"></i>
              <span class="menu-title">Insertion charge</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="inserCentre">
              <i class="icon-paper menu-icon"></i>
              <span class="menu-title">Insertion centre</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="repartition">
              <i class="icon-paper menu-icon"></i>
              <span class="menu-title">Répartition</span>
            </a>
          </li>
        </ul>
      </nav>
<!-- FIN MENU SIDEBAR ------------------------------------------------------------------------------------------------------>








      <!-- partial -->
      <div class="main-panel">
<!-- CONTENU  ------------------------------------------------------------------------------------------------------>
        <div class="content-wrapper">
            <div class="row">
                <div class="col-md-12 grid-margin">
                  <div class="row">
                    <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                      <h3 class="font-weight-bold">Gestion analytiques Far'in Company</h3>
                      <h6 class="font-weight-normal mb-0"></h6>
                    </div>
                  </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 grid-margin stretch-card">
                    <div class="card">
                      <div class="card-body">
                        <h4 class="card-title">Insertion charge</h4>
                        <form class="forms-sample"  action="addCharge" method="get">

                            <% if (charge != null) { %>
                              <input type="hidden" name="idCharge" value="<%= charge.getId() %>">
                            <% } %>

                            <div class="form-group">
                              <label for="rubrique">Rubrique</label>
                              <input type="text" class="form-control" name="rubrique" id="rubrique" placeholder="rubrique" value='<%= (charge != null) ? charge.getRubrique() : "" %>' required>
                            </div>
                            
                            <div class="form-group">
                                <label for="unite">Unité</label>
                                <div class="input-group">
                                    <select class="form-control" id="unite" name="unite">
                                        <% for (UniteOeuvre unite : uniteOeuvre) { %>
                                          <option value="<%= unite.getId() %>"
                                            <% if (charge != null && charge.getUniteOeuvre().getId() == unite.getId()) { %>
                                              selected
                                            <% } %>
                                          >  
                                            <%= unite.getLibele() %>
                                          </option>
                                        <% } %>
                                    </select>
                                  <div class="input-group-append">
                                    <button id="openModalBtn" class="btn btn-md-lg btn-primary" type="button">+</button>
                                  </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="nature">Nature</label>
                                <div class="row">
                                    <div class="form-check col-md-3 ml-3">
                                        <label class="form-check-label">
                                          <input type="radio" class="form-check-input" name="nature" id="fixe" value="V"
                                            <% if (charge != null && charge.getNature() == 'V') { %>
                                              checked
                                            <% } %>
                                          >
                                          Variable
                                        </label>
                                      </div>
                                      <div class="form-check col-md-3">
                                        <label class="form-check-label">
                                          <input type="radio" class="form-check-input" name="nature" id="variable" value="F" 
                                            <% if (charge != null && charge.getNature() == 'F') { %>
                                              checked
                                            <% } %>
                                          >
                                          Fixe
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="prix">Montant</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="sommeTotal"  placeholder="Entrez la somme totale" value='<%= (charge != null) ? charge.getPrix() : "" %>' min="1"  name="prix"  required>
                                <div class="input-group-append">
                                    <button id="openModalBtnCentre" class="btn btn-md btn-primary" type="button">Répartir sur les centres</button>
                                </div>
                                </div>
                            </div>



                            <div id="pourcentagesCentres" class="mt-3 mb-3">
                              <% if(centreCharge != null) { %>
                                  <h5>Répartition du montant par centre :</h5>
                                  <% for (CentreCharge item : centreCharge) { %>
                                    <div class="form-group"> 
                                        <label for="  <%=item.getCentre().getId()%>_pourcentage"> <%=item.getCentre().getLibele()%> :<%=item.getPrix()%>   Ar</label> 
                                        <div class="input-group"> 
                                            <div class="input-group-prepend"> 
                                                <span class="input-group-text">%</span> 
                                            </div>
                                            <input type="text" class="form-control" name="  <%=item.getCentre().getId()%>_pourcentage" id="  <%=item.getCentre().getId()%>_pourcentage" value="<%=item.getPourcentage()%>" readonly> 
                                            <input type="hidden" value=" prixxxx " name="  <%=item.getCentre().getId()%>_prix"> 
                                        </div>
                                    </div>
                              <%  }
                                } %>
                            </div>
                            
                            <button type="submit" class="btn btn-primary mr-2 col-md-3" >Valider</button>
                          </form>
                      </div>
                    </div>
                </div>
            </div>
        </div>

<!-- FIN CONTENU  ------------------------------------------------------------------------------------------------------>










<!-- MODAL UNITE  ------------------------------------------------------------------------------------------------------>
<section id="myModal" class=" modal" >
    <div class="card-body  modal-content"  style="width: 30%;" >
    <span class="close" >&times;</span>
        <h4 class="card-title">Insertion unité</h4>
        <!-- <div class="modal-content"  > -->
        <form class="forms-sample" action="ajoutUnite" method="get">
            <div class="form-group">
                <label for="uniteAjout">Libelé</label>
                <input type="text" class="form-control" name="uniteAjout" id="uniteAjout">
            </div>
            <button type="submit" class=" btn btn-primary mr-2 col-md-3" >Submit</button>
        </form>
    </div>
</section>
<!-- FIN MODAL UNITE  ------------------------------------------------------------------------------------------------------>











<!-- MODAL CENTRE  ------------------------------------------------------------------------------------------------------>
<section id="myModalCentre" class=" modal" >
    <div class="card-body  modal-content"  style="width: 30%;" >
    <span class="closeCentre" >&times;</span>
    <h4 class="card-title">Répartition du montant : <strong id="montantTotalModal" class="text-primary">1000</strong></h4>
        <!-- <div class="modal-content"  > -->
        <form class="forms-sample" id="repartitionForm" >
          <% 
            for (Centre centre : centres) { 
          %>
            <div class="form-group mt-2">
                <label for="c<%= centre.getId() %>" style="display: flex; align-items: center;"> <%= centre.getLibele() %>:
                    <div class="text-primary output ml-2" id="outputC<%= centre.getId() %>" ></div>
                </label>
                <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text">%</span>
                    </div>
                    <input type="number" class="form-control" id="c<%= centre.getId() %>" placeholder="" min="0" >
                </div>
            </div>
          <%
            } 
          %>
            <div class="error mb-3" id="error" style="display: none;"></div>
            <button type="submit" class="btn btn-primary">Soumettre</button>
        </form>
    </div>
</section>
<!-- FIN MODAL CENTRE  ------------------------------------------------------------------------------------------------------>







        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">

          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">
              Copyright © 2024. <a>Far'in Company</a> - Tous droits réservés.
            </span>
          </div>
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">
              Distribué par Far'in
            </span> 
          </div>
        </footer>
        
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>   
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->


  <script>
    const form = document.getElementById('repartitionForm');
    const sommeTotalInput = document.getElementById('sommeTotal');
    const errorDiv = document.getElementById('error');

    function updateOutputs() {
        const sommeTotal = parseFloat(sommeTotalInput.value) || 0;
        
        
        // Récupérer tous les inputs de centre et leurs sorties
        const centreInputs = document.querySelectorAll('input[id^="c"]');
        const outputDivs = document.querySelectorAll('div[id^="outputC"]');

        let totalPourcentage = 0;

        centreInputs.forEach((input, index) => {
            const pourcentage = parseFloat(input.value) || 0;
            totalPourcentage += pourcentage;

            // Calculer et afficher le montant correspondant
            const montant = (sommeTotal * pourcentage / 100).toFixed(2);
            outputDivs[index].innerText = montant;
        });

        // Vérification si le total dépasse 100
        if (totalPourcentage > 100) {
            errorDiv.innerText = "La somme des pourcentages ne doit pas dépasser 100.";
            errorDiv.style.display = "block";
        } else {
            errorDiv.style.display = "none";
        }
    }

    form.addEventListener('input', updateOutputs);

    
        


    form.addEventListener('submit', function(event) {
    event.preventDefault();
    const centreInputs = document.querySelectorAll('input[id^="c"]');
    let totalPourcentage = 0;
    const pourcentagesDiv = document.getElementById('pourcentagesCentres');

    centreInputs.forEach(input => {
        totalPourcentage += parseFloat(input.value) || 0;
    });

    if (totalPourcentage !== 100 && totalPourcentage !== 0) {
        event.preventDefault(); // Empêche la soumission
        errorDiv.innerText = "La somme des pourcentages doit être exactement 100 ou 0.";
        errorDiv.style.display = "block";
    } else if (totalPourcentage !== 100){
        errorDiv.style.display = "none";
        pourcentagesDiv.innerHTML = '';
        document.getElementById('myModalCentre').style.display = 'none';
    } else{
        errorDiv.style.display = "none"; // Cacher le message d'erreur
        let pourcentageHtml = '<h5>Répartition du montant par centre :</h5>';
        centreInputs.forEach((input) => {
            const inputId = input.id;
            const index = inputId.substring(1);
            var phrase = 'label[for="c'+index+'"]';
            const val = input.value === '' ? 0 : parseFloat(input.value);
            const centreLabel = document.querySelector(phrase).innerText;
            const sommeTotal = parseFloat(sommeTotalInput.value) || 0;
            let prix = (sommeTotal * val)/100;
 
            // Générer un input de type text pour chaque pourcentage
            pourcentageHtml +=
            '<div class="form-group">' +
                '<label for="' + index + '_pourcentage">' + centreLabel + ' Ar</label>' +
                '<div class="input-group">' +
                    '<div class="input-group-prepend">' +
                        '<span class="input-group-text">%</span>' +
                    '</div>' +
                    '<input type="text" class="form-control" name="' + index + '_pourcentage" id="' + index + '_pourcentage" value="' + val + '" readonly>' +
                    '<input type="hidden" value="' + prix + '" name="' + index + '_prix">' +
                '</div>' +
            '</div>';
        });
        pourcentagesDiv.innerHTML = pourcentageHtml;
        document.getElementById('myModalCentre').style.display = 'none';
    }
});










////////////// Pour griser le bouton Répartir en abscence de valeur sur l'input Montant
    const openModalBtnCentre = document.getElementById('openModalBtnCentre');
    // Désactiver le bouton par défaut
    openModalBtnCentre.disabled = true;

    // Activer ou désactiver le bouton en fonction de la valeur de l'input "Montant"
    sommeTotalInput.addEventListener('input', function() {
        if (sommeTotalInput.value.trim() === '' || parseFloat(sommeTotalInput.value) <= 0) {
            openModalBtnCentre.disabled = true; // Désactiver si la valeur est vide ou <= 0
        } else {
            openModalBtnCentre.disabled = false; // Activer si une valeur correcte est saisie
        }
    });






    // Sélectionner l'élément qui contient la valeur du montant dans le modal
    const montantTotalModal = document.getElementById('montantTotalModal');

    // Mettre à jour la valeur dans le modal lorsque le montant change
    sommeTotalInput.addEventListener('input', function() {
        const montant = parseFloat(sommeTotalInput.value) || 0; // Si c'est vide ou invalide, mettre à 0
        montantTotalModal.innerText = montant.toFixed(2); // Mettre à jour le texte dans le modal
    });


  </script>



  <!-- plugins:js -->
  <script src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="${pageContext.request.contextPath}/assets/vendors/chart.js/Chart.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="${pageContext.request.contextPath}/assets/js/off-canvas.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/hoverable-collapse.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/settings.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/todolist.js"></script>

  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="${pageContext.request.contextPath}/assets/js/dashboard.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->

  <script src="${pageContext.request.contextPath}/assets/js/vaovao.js"></script>
  

</body>

</html>

