<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,source.model.unite.*,source.model.centre.*,source.model.charge.*,source.model.centreCharge.*, source.model.divers.*" %>

<% 
  Map<String, Object> data = (Map<String, Object>) request.getAttribute("data");
  List<Repartition> listeRepartition = (List<Repartition>) data.get("listeRepartition");
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
        <a class="navbar-brand brand-logo mr-5" href="${pageContext.request.contextPath}"><img
            src="${pageContext.request.contextPath}/assets/images/logo_long.png" class="mr-2" alt="logo" /></a>
        <a class="navbar-brand brand-logo-mini" href="${pageContext.request.contextPath}"><img
            src="${pageContext.request.contextPath}/assets/images/logo.png" alt="logo" /></a>
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
          <div class="sidebar-bg-options selected" id="sidebar-light-theme">
            <div class="img-ss rounded-circle bg-light border mr-3"></div>Sombre
          </div>
          <div class="sidebar-bg-options" id="sidebar-dark-theme">
            <div class="img-ss rounded-circle bg-dark border mr-3"></div>Clair
          </div>
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
                  <h4 class="card-title">Tableau de répartition </h4>
                  <p class="card-description">
                    production <code> Far'in Company</code>
                  </p>
                  <div class="table-responsive">
                    <table class="table table-bordered">
<<<<<<< HEAD
                        <thead>
                          <tr>
                              <th>Repartition ADM/DISTR</th>
                              <th>Cout direct</th>
                              <th>Cles</th>
                              <th>ADM/DISTR</th>
                              <th>Cout total</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>Total Usine</td>
                            <td></td>
                            <td>92.40%</td>
                            <td>309088.2353</td>
                            <td>2 060 588</td>
                          </tr>
                          <tr>
                            <td>Total Laboratoire</td>
                            <td>144 000</td>
                            <td>7.60%</td>
                            <td>25411.76471</td>
                            <td>165 412</td>
                          </tr>
                        </tbody>
                        <tfoot>
                          <tr>
                            <td>Total Generale</td>
                            <td>1 895 500</td>
                            <td></td>
                            <td>334500</td>
                            <td>2 230 000</td>
                          </tr>
                        </tfoot>
=======
                      <thead>
                        <tr>
                          <th>Repartition <%= data.get("nomStructurel") %></th>
                          <th>Cout direct</th>
                          <th>Cles</th>
                          <th><%= data.get("nomStructurel") %></th>
                          <th>Cout total</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                          for (Repartition rep : listeRepartition) {
                        %>
                            <tr>
                              <td>Total <%= rep.getNomCentre() %></td>
                              <td><%= rep.getCoutDirecte() %></td>
                              <td><%= rep.getCle() %>%</td>
                              <td><%= rep.getRepartition() %></td>
                              <td><%= rep.getCoutTotal() %></td>
                            </tr>
                        <% 
                          }
                        %>
                        
                      </tbody>
                      <tfoot>
                        <tr>
                          <td>Total Generale</td>
                          <td><%= data.get("totalCoutDirecte") %></td>
                          <td> - </td>
                          <td><%= data.get("totalARepartir") %></td>
                          <td><%= data.get("totalCoutTotal") %></td>
                        </tr>
                      </tfoot>
>>>>>>> ffe5638b74bd05ee10957870df00d8fc6daf4226
                      <tfoot>
                      </tfoot>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <form action="repart" method="get">
            <div class="form-row align-items-center">
              <div class="form-group col-md-8">
                <label for="centreAjout">Quantité de blé :</label>
                <input type="number" class="form-control" name="quantite" placeholder="Quantité">
              </div>
              <div class="form-group col-md-4 d-flex align-items-end">
                <button type="submit" class="btn btn-primary">Valider</button>
              </div>
            </div>
          </form>

          <div class="row"></div>
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Cout du KG de farine </h4>
                  
                  <div class="table-responsive">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>Unite</th>
                          <td>KG</td>
                        </tr>
                        <% for(Repartition rep : listeRepartition) { %>
                          <tr>
                            <th><%= rep.getNomCentre() %></th>
                            <td><%= rep.getCoutTotal() %></td>
                          </tr>

                        <% } %>
                        <tr>
                          <th>COUT TOTAUX</th>
                          <td><%= data.get("totalCoutTotal") %></td>
                        </tr>

                        <tr>
                          <th>Quantite de farine obtenu</th>
                          <td><%= data.get("quantite") %></td>
                        </tr>
                        <tr style="background-color: rgb(127, 202, 156);">
                          <th>COUT DU KG de farine</th>
                          <td><%= data.get("seuil") %></td>
                        </tr>
                        <tr>
                          <th>Valeur ajouter</th>
                          <td><%= data.get("valeurAjouter") %></td>
                        </tr>
                        <tr>
                          <th>Prix unitaire</th>
                          <td><%= data.get("pu") %></td>
                        </tr>
                        <tr style="background-color: rgb(83, 243, 144);">
                          <th>TOTAL benefice</th>
                          <td><%= data.get("totalBenefice") %></td>
                        </tr>
                      </thead>
                      
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>


        </div>
        <!--FIN CONTENU ------------------------------------------------------------------------------------------------------>








        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <!-- <footer class="footer">
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
        </footer> -->

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