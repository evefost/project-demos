var app_root_path = "";
var MediType_FORM_URLENCODE = "application/x-www-form-urlencoded; charset=UTF-8";
var MediType_JSON = "application/json; charset=UTF-8";

if (!Api) {
    var Api = {
        debug: true,
        ajaxDemo: {
            formAdd: {
                url: app_root_path + "/api/ajax/formAdd",
                method: "POST",
                contentType: MediType_FORM_URLENCODE,
            },
            postBody: {
                url: app_root_path + "/api/ajax/postBody",
                method: "POST",
                contentType: MediType_JSON
            },
            getRequest: {
                url: app_root_path + "/api/ajax/getRequest",
                method: "GET",
                contentType: MediType_FORM_URLENCODE
            }

        },
        account_modle: {
            addAccount: {
                url: app_root_path + "admin/account/add",
                method: "POST",
                contentType: MediType_JSON,
            },
            accountListByPage: {
                url: app_root_path + "admin/account/add",
                method: "GET",
                contentType: MediType_FORM_URLENCODE
            }

        },
        order_modle: {
            addOffLineOrder: {
                url: app_root_path + "admin/account/add",
                method: "POST",
                contentType: MediType_JSON
            },
            contractByPage: {
                url: app_root_path + "/order/contract/queryByPage",
                method: "GET",
                contentType: MediType_FORM_URLENCODE
            }

        },
        dict_modle: {
            addOrUpdate: {
                url: app_root_path + "/dicttionary/detail/addOrUpdate",
                method: "POST",
                contentType: MediType_JSON
            }

        }
    }
}


if (!NetUtis) {

    var NetUtis = {
        doRequest:function (api, params) {
            this.doLogBefore(api, params)
            if(api.method == "GET"){
                this.doGet(api,params);
            }else  if(api.method == "POST"){
                if(api.contentType == MediType_JSON){
                    this.doPostBody(api,JSON.stringify(params));
                }else {
                    this.doPostForm(api,params);
                }
            }else{

            }
        },
        doGet:function (api, params) {
            $.ajax({
                type: "GET", //请求方式
                url: api.url, //请求地址
                dataType: "json",//client接收数据类型
                data: params,
                headers: {'Content-Type': ' application/json; charset=UTF-8'},
                success: function (data) {
                    if (Api.debug) {
                        console.log("请求结果:" + JSON.stringify(data))
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("请求信息失败 status:" + textStatus)
                }

            });
        },
        doPostForm:function (api, params) {
            $.post(api.url, params, function (data) {
                if (Api.debug) {
                    console.log("请求结果:" + JSON.stringify(data))
                }
            });
        },
        doPostBody:function (api, params) {
            $.ajax({
                type: "POST", //请求方式
                url: api.url, //请求地址
                dataType: "json",//client接收数据类型
                data: params,
                headers: {'Content-Type': ' application/json; charset=UTF-8'},
                success: function (data) {
                    if (Api.debug) {
                        console.log("请求结果:" + JSON.stringify(data))
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("请求信息失败 status:" + textStatus)
                }
            });
        },
        doLogBefore: function (api, reqParams) {

            if (Api.debug) {
                console.log("发送请求信息:" + JSON.stringify(api));
                console.log("请求参数:" + JSON.stringify(reqParams));
                if (api.method == undefined) {
                    console.warn("没指定请求方式，使用默认的GET试请求");
                }
                if (api.contentType == undefined) {
                    console.warn("没指定发送能数方式，使用默认的application/x-www-form-urlencoded");
                }
            }
        }
    };
}






