/*$(function(){*/
angular.module('myApp', []).controller('queryController', queryController);
queryController.$inject = ['$scope', '$http'];

function queryController($scope, $http) {
    console.log("1111111111111111");

    $scope.baseAmount = '';
    $scope.addTime = '';
    $scope.getAmount = '';
    $scope.firstAmount = '';
    $scope.secondAmount = '';
    $scope.outAmount = '';
    $scope.queryAmount = function () {
        console.log($scope.baseAmount);
        $.ajax({
            url: '/query/count',
            type: 'get',
            data: {base: $scope.baseAmount, time: $scope.addTime},
            async: false,
            success: function (result) {
                console.log(result);
                /*$scope.getAmount=result.sumAmout
                $scope.firstAmount=result.getFirstAmount;
                $scope.secondAmount=result.getSecondAmout;
                $scope.outAmount=result.getSecondAmout;*/
            }

        })
        /*$http({
                method: 'GET',
                url: '/query/count',
                data: {base: $scope.baseAmount, time: $scope.addTime}
               // headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data) {
            console.log(data);
        })*/
    }

}


/*$('button.toquery').click(function(){
    var baseamount =$('input.baseAmount').val();
    var addTime =$('input.addTime').val();
    var map={};
    map.base=baseamount;
    map.time=addTime;
    console.log("1111111111111111");
    console.log(map);
    $.ajax({
        url:'/query/count',
        type:'get',
        data:{base:baseamount,time:addTime},
        async:false,
        success:function(result){
            console.log(result);
            $('input.getAmount').val(result);
        }

    })
})*/


/*})*/
