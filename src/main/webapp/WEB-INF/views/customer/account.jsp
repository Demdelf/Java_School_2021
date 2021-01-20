<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.01.2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.79.0">
    <title>Checkout example · Bootstrap v5.0</title>

    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">--%>



    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="resources/css/form-validation.css" rel="stylesheet">
</head>


<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <p class="h5 my-0 me-md-auto fw-normal">Company name</p>
    <nav class="my-2 my-md-0 me-md-3">
        <a class="p-2 text-dark" href="#">Features</a>
        <a class="p-2 text-dark" href="#">Enterprise</a>
        <a class="p-2 text-dark" href="#">Support</a>
        <a class="p-2 text-dark" href="#">Pricing</a>
    </nav>
    <a class="btn btn-outline-primary" href="#">Account</a>
</header>

<body class="bg-light">

<div class="container">
    <div class="row my-2">
        <div class="col-lg-8 order-lg-2">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="" data-target="#profile" data-toggle="tab" class="nav-link active">Profile</a>
                </li>
                <li class="nav-item">
                    <a href="" data-target="#messages" data-toggle="tab" class="nav-link">Orders</a>
                </li>
                <li class="nav-item">
                    <a href="" data-target="#edit" data-toggle="tab" class="nav-link">Edit</a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <h5 class="mb-3">User Profile</h5>
                    <div class="row">
                        <div class="col-md-6">
                            <h6>About</h6>
                            <p>
                                Web Designer, UI/UX Engineer
                            </p>
                            <h6>Hobbies</h6>
                            <p>
                                Indie music, skiing and hiking. I love the great outdoors.
                            </p>
                        </div>
<%--                        <div class="col-md-6">--%>
<%--                            <h6>Recent badges</h6>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">html5</a>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">react</a>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">codeply</a>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">angularjs</a>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">css3</a>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">jquery</a>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">bootstrap</a>--%>
<%--                            <a href="#" class="badge badge-dark badge-pill">responsive-design</a>--%>
<%--                            <hr>--%>
<%--                            <span class="badge badge-primary"><i class="fa fa-user"></i> 900 Followers</span>--%>
<%--                            <span class="badge badge-success"><i class="fa fa-cog"></i> 43 Forks</span>--%>
<%--                            <span class="badge badge-danger"><i class="fa fa-eye"></i> 245 Views</span>--%>
<%--                        </div>--%>
                        <div class="col-md-12">
                            <h5 class="mt-2"><span class="fa fa-clock-o ion-clock float-right"></span> Recent Activity</h5>
                            <table class="table table-sm table-hover table-striped">
                                <tbody>
                                <tr>
                                    <td>
                                        <strong>Email</strong> ${accountDto.email}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>FirstName</strong> ${accountDto.email}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>LastName</strong> ${accountDto.email}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>BirthDay</strong> ${accountDto.email}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>Skell</strong> deleted his post Look at Why this is.. in <strong>`Discussions`</strong>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!--/row-->
                </div>
                <div class="tab-pane" id="messages">
                    <div class="alert alert-info alert-dismissable">
                        <a class="panel-close close" data-dismiss="alert">×</a> This is an <strong>.alert</strong>. Use this to show important messages to the user.
                    </div>
                    <table class="table table-hover table-striped">
                        <tbody>
                        <tr>
                            <td>
                                <span class="float-right font-weight-bold">3 hrs ago</span> Here is your a link to the latest summary report from the..
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="float-right font-weight-bold">Yesterday</span> There has been a request on your account since that was..
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="float-right font-weight-bold">9/10</span> Porttitor vitae ultrices quis, dapibus id dolor. Morbi venenatis lacinia rhoncus.
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="float-right font-weight-bold">9/4</span> Vestibulum tincidunt ullamcorper eros eget luctus.
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="float-right font-weight-bold">9/4</span> Maxamillion ais the fix for tibulum tincidunt ullamcorper eros.
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="edit">
                    <form role="form">
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">First name</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" value="Jane">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Last name</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" value="Bishop">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Email</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="email" value="email@gmail.com">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Company</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" value="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Website</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="url" value="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Address</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" value="" placeholder="Street">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-6">
                                <input class="form-control" type="text" value="" placeholder="City">
                            </div>
                            <div class="col-lg-3">
                                <input class="form-control" type="text" value="" placeholder="State">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Time Zone</label>
                            <div class="col-lg-9">
                                <select id="user_time_zone" class="form-control" size="0">
                                    <option value="Hawaii">(GMT-10:00) Hawaii</option>
                                    <option value="Alaska">(GMT-09:00) Alaska</option>
                                    <option value="Pacific Time (US &amp; Canada)">(GMT-08:00) Pacific Time (US &amp; Canada)</option>
                                    <option value="Arizona">(GMT-07:00) Arizona</option>
                                    <option value="Mountain Time (US &amp; Canada)">(GMT-07:00) Mountain Time (US &amp; Canada)</option>
                                    <option value="Central Time (US &amp; Canada)" selected="selected">(GMT-06:00) Central Time (US &amp; Canada)</option>
                                    <option value="Eastern Time (US &amp; Canada)">(GMT-05:00) Eastern Time (US &amp; Canada)</option>
                                    <option value="Indiana (East)">(GMT-05:00) Indiana (East)</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Username</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" value="janeuser">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Password</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="password" value="11111122333">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Confirm password</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="password" value="11111122333">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-9">
                                <input type="reset" class="btn btn-secondary" value="Cancel">
                                <input type="button" class="btn btn-primary" value="Save Changes">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-4 order-lg-1 text-center">
            <img src="//placehold.it/150" class="mx-auto img-fluid img-circle d-block" alt="avatar">
            <h6 class="mt-2">Upload a different photo</h6>
            <label class="custom-file">
                <input type="file" id="file" class="custom-file-input">
                <span class="custom-file-control">Choose file</span>
            </label>
        </div>
    </div>
</div>






<div class="container">
    <div class="row">
        <div class="col-md-12">
            <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div id="content" class="content content-full-width">
                            <!-- begin profile -->
                            <div class="profile">
                                <div class="profile-header">
                                    <!-- BEGIN profile-header-cover -->
                                    <div class="profile-header-cover"></div>
                                    <!-- END profile-header-cover -->
                                    <!-- BEGIN profile-header-content -->
                                    <div class="profile-header-content">
                                        <!-- BEGIN profile-header-img -->
                                        <div class="profile-header-img">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="">
                                        </div>
                                        <!-- END profile-header-img -->
                                        <!-- BEGIN profile-header-info -->
                                        <div class="profile-header-info">
                                            <h4 class="m-t-10 m-b-5">${accountDto.email}</h4>
                                            <p class="m-b-10"></p>
                                            <a href="#" class="btn btn-xs btn-success">Edit Profile</a>
                                        </div>
                                        <!-- END profile-header-info -->
                                    </div>
                                    <!-- END profile-header-content -->
                                    <!-- BEGIN profile-header-tab -->
                                    <ul class="profile-header-tab nav nav-tabs">
                                        <li class="nav-item"><a href="#profile-post" class="nav-link" data-toggle="tab">POSTS</a></li>
                                        <li class="nav-item"><a href="#profile-about" class="nav-link active show" data-toggle="tab">ABOUT</a></li>
                                        <li class="nav-item"><a href="#profile-photos" class="nav-link" data-toggle="tab">PHOTOS</a></li>
                                        <li class="nav-item"><a href="#profile-videos" class="nav-link" data-toggle="tab">VIDEOS</a></li>
                                        <li class="nav-item"><a href="#profile-friends" class="nav-link" data-toggle="tab">FRIENDS</a></li>
                                    </ul>
                                    <!-- END profile-header-tab -->
                                </div>
                            </div>
                            <!-- end profile -->
                            <!-- begin profile-content -->
                            <div class="profile-content">
                                <!-- begin tab-content -->
                                <div class="tab-content p-0">

                                    <!-- begin #profile-about tab -->
                                    <div class="tab-pane fade in active show" id="profile-about">
                                        <!-- begin table -->
                                        <div class="table-responsive">
                                            <table class="table table-profile">
                                                <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>
                                                        <h4>Micheal    Meyer <small>Lorraine Stokes</small></h4>
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr class="highlight">
                                                    <td class="field">Mood</td>
                                                    <td><a href="javascript:;">Add Mood Message</a></td>
                                                </tr>
                                                <tr class="divider">
                                                    <td colspan="2"></td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Mobile</td>
                                                    <td><i class="fa fa-mobile fa-lg m-r-5"></i> +1-(847)- 367-8924 <a href="javascript:;" class="m-l-5">Edit</a></td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Home</td>
                                                    <td><a href="javascript:;">Add Number</a></td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Office</td>
                                                    <td><a href="javascript:;">Add Number</a></td>
                                                </tr>
                                                <tr class="divider">
                                                    <td colspan="2"></td>
                                                </tr>
                                                <tr class="highlight">
                                                    <td class="field">About Me</td>
                                                    <td><a href="javascript:;">Add Description</a></td>
                                                </tr>
                                                <tr class="divider">
                                                    <td colspan="2"></td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Country/Region</td>
                                                    <td>
                                                        <select class="form-control input-inline input-xs" name="region">
                                                            <option value="US" selected="">United State</option>
                                                            <option value="AF">Afghanistan</option>
                                                            <option value="AL">Albania</option>
                                                            <option value="DZ">Algeria</option>
                                                            <option value="AS">American Samoa</option>
                                                            <option value="AD">Andorra</option>
                                                            <option value="AO">Angola</option>
                                                            <option value="AI">Anguilla</option>
                                                            <option value="AQ">Antarctica</option>
                                                            <option value="AG">Antigua and Barbuda</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="field">City</td>
                                                    <td>Los Angeles</td>
                                                </tr>
                                                <tr>
                                                    <td class="field">State</td>
                                                    <td><a href="javascript:;">Add State</a></td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Website</td>
                                                    <td><a href="javascript:;">Add Webpage</a></td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Gender</td>
                                                    <td>
                                                        <select class="form-control input-inline input-xs" name="gender">
                                                            <option value="male">Male</option>
                                                            <option value="female">Female</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Birthdate</td>
                                                    <td>
                                                        <select class="form-control input-inline input-xs" name="day">
                                                            <option value="04" selected="">04</option>
                                                        </select>
                                                        -
                                                        <select class="form-control input-inline input-xs" name="month">
                                                            <option value="11" selected="">11</option>
                                                        </select>
                                                        -
                                                        <select class="form-control input-inline input-xs" name="year">
                                                            <option value="1989" selected="">1989</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="field">Language</td>
                                                    <td>
                                                        <select class="form-control input-inline input-xs" name="language">
                                                            <option value="" selected="">English</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr class="divider">
                                                    <td colspan="2"></td>
                                                </tr>
                                                <tr class="highlight">
                                                    <td class="field">&nbsp;</td>
                                                    <td class="p-t-10 p-b-10">
                                                        <button type="submit" class="btn btn-primary width-150">Update</button>
                                                        <button type="submit" class="btn btn-white btn-white-without-border width-150 m-l-5">Cancel</button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- end table -->
                                    </div>
                                    <!-- end #profile-about tab -->
                                </div>
                                <!-- end tab-content -->
                            </div>
                            <!-- end profile-content -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

<script src="resourced/js/form-validation.js"></script>
</body>
</html>
