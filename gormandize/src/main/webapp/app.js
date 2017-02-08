(function() {
	'use strict';
	var app = angular.module("myapp", []);

	app
			.controller(
					"newcontroller1",
					[
							'$scope',
							'$http',
							'$rootScope',
							function($scope, $http, $rootScope) {

								$scope.hotel = [];
								$http(
										{
											method : 'GET',
											url : 'webapi/restaurants/view',
											contentType : 'application/json; charset=utf-8',
											dataType : 'application/json',
										}).then(
										function successCallback(response) {
											// window.alert(response.data[0].restaurant_name);
											// console.log(response.data);
											$scope.hotel = response.data;
										}, function errorCallback(response) {
											// console.log(response.data);

										});
								$scope.showSelectValue = function(showrating) {
									console.log(showrating);
								}

								$scope.register = function() {
									$('#modal2').openModal();
								};

								$scope.addreview = function() {
									$('#rev').openModal();
								};
								$scope.addreview_existing = function(na/* avrate */) {
									$('#rev_exist').openModal();
									$scope.na = na;
									// $scope.avrate=avrate;
								};
								$scope.login = function() {
									$('#login').openModal();
								}

								$scope.about_us = function() {
									$('#modal3').openModal();
								};

								$scope.getstars_for_review = function(m) {
									return new Array(m);
								};

								$scope.getstars = function(n) {
									return new Array(n);
								};

								$scope.clearSearch = function() {
									$scope.searchAll = null;
								};

								// login
								$scope.login_get = function() {
									// $rootScope.currentUserSignedIn = false;
									$http(
											{
												method : 'GET',
												contentType : 'application/json; charset=utf-8',
												dataType : 'application/json',
												url : 'webapi/users/login/'
														+ $scope.login_uname
														+ '/'
														+ $scope.login_pwd

											})
											.then(
													function successCallback(
															response) {													

														if (response.data == $scope.login_uname) {
															$rootScope.currentUserSignedIn = true;
															$rootScope.currentUser = response.data;
														}
														

														else {

															window.alert("invalid user");
															$scope.login_uname="";
															$scope.login_pwd="";
														}

														// console.log(response);

													},
													function errorCallback(
															error) {
														console.log(error);
													});
								};

								// logout
								$scope.logout = function() {
									$rootScope.currentUserSignedIn = false;
								}

								// star rating
								$scope.starRating1 = 4;
								$scope.hoverRating1 = $scope.hoverRating2 = $scope.hoverRating3 = 0;

								$scope.click1 = function(param) {
									console.log('Click(' + param + ')');
								};

								$scope.mouseHover1 = function(param) {
									console.log('mouseHover(' + param + ')');
									$scope.hoverRating1 = param;
								};

								$scope.mouseLeave1 = function(param) {
									console.log('mouseLeave(' + param + ')');
									$scope.hoverRating1 = param + '*';
								};

								// post review existing
								$scope.post_review_exist = function(r, na) {

									$http(
											{
												method : 'POST',
												url : 'webapi/reviews/review_exist_update/'
														+ na.restaurant_name,
												data : {
													"username" : $scope.currentUser,
													"restaurant_id" : na.restaurant_id,
													"review" : r.new_comment1,
													"rating" : r.showrating,
													"average_rating" : na.average_rating
												
												}
											}).then(
											function successCallback(response) {
												r.new_comment1="";
												r.showrating="";												

											},
											function errorCallback(response) {

											});

								};

								// post review for new restaurant
								$scope.post_review = function(s) {

									$http(
											{
												method : 'POST',
												url : 'webapi/restaurants/add',
												data : {
													"username" : $scope.currentUser,
													"restaurant_name" : $scope.new_res_name,
													"review" : $scope.new_comment,
													// "rating":s.showrating,
													"average_rating" : s.showrating
												}

											}).then(
											function successCallback(response) {
												$scope.new_res_name="";
												$scope.new_comment="";
												s.showrating="";

											},
											function errorCallback(response) {

											});

								};
								// see reviews for particular restaurant
								$scope.see_reviews = function(na1) {
									$('#seereviewModal').openModal();
									$scope.na1 = na1;

									$http(
											{
												method : 'GET',
												contentType : 'application/json; charset=utf-8',
												dataType : 'application/json',
												url : 'webapi/reviews/view/'
														+ $scope.na1

											}).then(
											function successCallback(response) {
												console.log(response.data);
												$scope.na1 = response.data;

											},
											function errorCallback(response) {
												console.log(error);
											});

								};
							} ]);

	app.controller('RegController', [ '$scope', '$http',
			function($scope, $http) {
				$scope.master = {};

				$scope.update = function(user) {
					$scope.master = angular.copy(user);
				};

				$scope.update = function() {

					$http({
						method : 'POST',
						contentType : 'application/json; charset=utf-8',
						dataType : 'application/json',
						url : 'webapi/users/signup',
						data : {
							"firstname" : $scope.fname,
							"lastname" : $scope.lname,
							"username" : $scope.uname,
							"password" : $scope.pwd,
							"email" : $scope.email
						}
					}).then(function successCallback(response) {
						$scope.fname = "";
						$scope.lname = "";
						$scope.uname = "";
						$scope.pwd = "";
						$scope.email = "";
					}, function errorCallback(response) {

					});
				};
			} ]);

})();
