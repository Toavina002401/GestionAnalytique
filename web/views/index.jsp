<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,source.model.unite.*,source.model.centre.*,source.model.charge.*,source.model.centreCharge.*" %>

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
                  <h6 class="font-weight-normal mb-0">Calcul du coût de revient de la production de farine</h6>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Tableau de répartition analytique</h4>
                  <p class="card-description">
                    production <code> Far'in Company</code>
                  </p>
                  <div class="table-responsive">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                            <th rowspan="2">Rubrique</th>
                            <th rowspan="2">Total Prix</th>
                            <th rowspan="2">Unité d'oeuvre</th>
                            <th rowspan="2">Nature</th>
                            <%
                            // Récupérer les centres
                            List<Centre> centres = (List<Centre>) request.getAttribute("listeCentre");
                            // Boucler sur les centres pour créer les en-têtes
                            for (Centre centre : centres) {
                            %>
                                <th colspan="3"><%= centre.getLibele() %></th>
                            <%
                            }
                            %>
                            <th rowspan="2">Action</th>
                        </tr>
                        <tr>
                            <%
                            // Boucler de nouveau pour les sous-en-têtes
                            for (Centre centre : centres) {
                            %>
                                <th>%</th>
                                <th>FIXE</th>
                                <th>VARIABLE</th>
                            <%
                            }
                            %>
                        </tr>
                    </thead>
                    <tbody>
                      <%
                      // Récupérer les objets Charge et CentreCharge à partir du modèle
                      List<Charge> allCharge = (List<Charge>) request.getAttribute("allCharge");
                      List<CentreCharge> allCentreCharge = (List<CentreCharge>) request.getAttribute("allCentreCharge");
          
                      // Itérer à travers allCharge pour afficher les détails
                      for (Charge charge : allCharge) {
                          // Initialiser les valeurs à zéro pour chaque charge
                          double totalPrix = charge.getPrix(); // On suppose que chaque charge a un prix total
                          String uniteOeuvre = charge.getUniteOeuvre().getLibele(); // Assurez-vous que cette méthode existe
                          char nature = charge.getNature(); // Nature de la charge
          
                          // Début de la ligne pour la charge
                      %>
                          <tr>
                              <td><%= charge.getRubrique() %></td>
                              <td><%= totalPrix %></td>
                              <td><%= uniteOeuvre %></td>
                              <td><%= nature %></td>
                              <%
                              // Itérer sur les centres pour afficher les valeurs des CentreCharge
                              for (Centre centre : centres) {
                                  // Chercher la CentreCharge correspondante
                                  CentreCharge centreCharge = null;
                                  for (CentreCharge cc : allCentreCharge) {
                                      if (cc.getCentre().getId() == centre.getId() && cc.getCharge().getId() == charge.getId()) {
                                          centreCharge = cc;
                                          break;
                                      }
                                  }
                                  // Si la CentreCharge est trouvée, afficher ses valeurs, sinon laisser vide
                                  if (centreCharge != null) {
                              %>
                                  <td><%= centreCharge.getPourcentage() %></td>
                                  <td>
                                      <%
                                          if(nature=='F'){
                                              out.print(centreCharge.getPrix());
                                          }else{
                                              out.print("-");
                                          } 
                                      %>
                                  </td>
                                  <td>
                                      <%
                                          if(nature=='V'){
                                              out.print(centreCharge.getPrix());
                                          }else{
                                              out.print("-");
                                          } 
                                      %>
                                  </td> <!-- Calculer la variable -->
                              <%
                                  } else {
                              %>
                                  <td></td>
                                  <td></td>
                                  <td></td>
                              <%
                                  }
                              }
                              %>
                              <td>
                                <a href="supr?id=<%= charge.getId() %>">
                                  <img style="width: 17px;height: 17px;" src="${pageContext.request.contextPath}/assets/images/corbeille.png">
                                </a>
                              </td>
                          </tr>
                      <%
                      }
                      %>
                  </tbody>
                  <tfoot>
                      <tr>
                          <td></td>
                          <td></td>
                          <td></td>
                          <td></td>
                          <%
                              double[] prixVariable = (double[])request.getAttribute("totalVariableCentre");
                              double[] prixFixe = (double[])request.getAttribute("totalFixeCentre");
                              for (int i = 0; i < centres.size(); i++) { 
                                  if(centres.size()-1 == 0){ 
                                      break;
                                  } else {  
                          %>
                                  <td></td>
                                  <td><%= prixFixe[i] %></td>
                                  <td><%= prixVariable[i] %></td>
                              <% }%>
                          <% } %>
                      </tr>
                      <tr>
                          <td>Total</td>
                          <td><%= (new Charge()).totalPrixCharge() %></td>
                          <td></td>
                          <td></td>
                          <%
                              for (int i = 0; i < centres.size(); i++) {  
                          %>
                          <td colspan="3" style="text-align: center;">
                              <%
                                  try{
                                      out.print(prixVariable[i] + prixFixe[i]);
                                  } catch (Exception e) {
                                      out.print("Erreur: " + e.getMessage());
                                  }
                              %>
                          </td>
                          <% } %>
                      </tr>
                  </tfoot>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
<!--FIN MENU SIDEBAR ------------------------------------------------------------------------------------------------------>
      







        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">
              Copyright © 2024. <a>Far'in</a> - Tous droits réservés.
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

