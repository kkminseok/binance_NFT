(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0baf9879"],{"578a":function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("v-app",{attrs:{id:"inspire"}},[s("v-main",{staticClass:"blue-grey lighten-1"},[s("v-container",{staticStyle:{"max-width":"450px"},attrs:{"fill-height":""}},[s("v-layout",{attrs:{"align-center":"",row:"",wrap:""}},[s("v-flex",{attrs:{xs12:""}},[s("v-card",[s("div",{staticClass:"pa-5"},[s("h1",{staticClass:"mb-5",staticStyle:{"text-align":"center"}},[t._v("Login")]),s("form",[s("v-text-field",{attrs:{label:"ID","prepend-inner-icon":"mdi-account"},model:{value:t.uid,callback:function(e){t.uid=e},expression:"uid"}}),s("v-text-field",{attrs:{"prepend-inner-icon":"mdi-lock",type:"password",label:"password"},model:{value:t.password,callback:function(e){t.password=e},expression:"password"}}),s("v-btn",{staticClass:"mb-3",attrs:{type:"submit",color:"blue lighten-2 text-capitalize",depressed:"",large:"",block:"",dark:""},on:{click:t.login}},[t._v(" Sign in ")]),s("v-btn",{staticClass:"mb-3",attrs:{type:"submit",color:"red lighten-3 text-capitalize",depressed:"",large:"",block:"",dark:"","router-link":"",to:"/signup"}},[t._v("Sign up ")])],1),s("span",[t._v(" 사용자 정보 : "),s("span",[t._v(t._s(t.message))])]),s("hr"),s("v-btn",{attrs:{id:"getInfo"},on:{click:t.getInfo}},[t._v(" 정보확인 ")]),s("v-btn",{attrs:{id:"logout"},on:{click:t.logout}},[t._v(" 로그아웃 ")]),s("hr"),s("v-simple-table",[s("tr",[s("th",[t._v("status")]),s("td",{attrs:{id:"status"}},[t._v(t._s(t.status))])]),s("tr",[s("th",[t._v("token")]),s("td",{attrs:{id:"token"}},[t._v(t._s(t.token))])]),s("tr",[s("th",[t._v("refresh token")]),s("td",{attrs:{id:"refreshtoken"}},[t._v(t._s(t.refreshtoken))])]),s("tr",[s("th",[t._v("info")]),s("td",{attrs:{id:"info"}},[t._v(t._s(t.info))])])])],1)])],1)],1)],1)],1)],1)},n=[],i=(s("e9c4"),window.sessionStorage),o={data:function(){return{uid:null,password:null,message:"message test",status:"",token:"",refreshtoken:"",info:""}},methods:{setInfo:function(t,e,s,a){this.status=t,this.token=e,this.refreshtoken=s,this.info=a},logout:function(){i.setItem("jwt-auth-token",""),i.setItem("login_user",""),this.message="login plz",this.setInfo("logout success","","")},getInfo:function(){var t=this;console.log(this.uid,this.password),this.$http.post("/user/info",{uid:this.uid,password:this.password},{headers:{"jwt-auth-token":i.getItem("jwt-auth-token")}}).then((function(e){t.setInfo("정보 조회 성공,"+e.headers["jwt-auth-token"],e.headers["jwt-auth-refresh-token"],JSON.stringify(e.data))})).catch((function(e){t.setInfo("fail to info",e.response.data.msg)}))},login:function(){var t=this;i.setItem("jwt-auth-token",""),i.setItem("jwt-auth-refresh-token",""),i.setItem("login_user",""),this.$http.post("/auth/signin",{uid:this.uid,password:this.password}).then((function(e){e.data.status?(t.message=e.data.data.uid+"login",console.dir(e.headers["jwt-auth-token"]),t.setInfo("login complete",e.headers["jwt-auth-token"],e.headers["jwt-auth-refresh-token"],JSON.stringify(e.data.data)),i.setItem("jwt-auth-token",e.headers["jwt-auth-token"]),i.setItem("jwt-auth-refresh-token",e.headers["jwt-auth-refresh-token"]),i.setItem("login_user",e.data.data.uid)):(t.setInfo("","",""),t.message="login plz",alert("error! input correct id or password"))})).catch((function(e){t.setInfo("fail","",JSON.stringify(e.response||e.message))}))},init:function(){i.getItem("jwt-auth-token")?this.message=i.getItem("login_user로 로그인"):(i.setItem("jwt-auth-token",""),i.setItem("jwt-auth-refresh-token",""))},signup:function(){}},mounted:function(){this.init()}},r=o,l=s("2877"),d=s("6544"),h=s.n(d),u=s("7496"),c=s("8336"),f=s("b0af"),p=s("a523"),g=s("0e8f"),m=s("a722"),v=s("f6c4"),b=s("5530"),w=(s("a9e3"),s("8b37"),s("80d2")),k=s("7560"),_=s("58df"),I=Object(_["a"])(k["a"]).extend({name:"v-simple-table",props:{dense:Boolean,fixedHeader:Boolean,height:[Number,String]},computed:{classes:function(){return Object(b["a"])({"v-data-table--dense":this.dense,"v-data-table--fixed-height":!!this.height&&!this.fixedHeader,"v-data-table--fixed-header":this.fixedHeader,"v-data-table--has-top":!!this.$slots.top,"v-data-table--has-bottom":!!this.$slots.bottom},this.themeClasses)}},methods:{genWrapper:function(){return this.$slots.wrapper||this.$createElement("div",{staticClass:"v-data-table__wrapper",style:{height:Object(w["d"])(this.height)}},[this.$createElement("table",this.$slots.default)])}},render:function(t){return t("div",{staticClass:"v-data-table",class:this.classes},[this.$slots.top,this.genWrapper(),this.$slots.bottom])}}),j=s("8654"),x=Object(l["a"])(r,a,n,!1,null,null,null);e["default"]=x.exports;h()(x,{VApp:u["a"],VBtn:c["a"],VCard:f["a"],VContainer:p["a"],VFlex:g["a"],VLayout:m["a"],VMain:v["a"],VSimpleTable:I,VTextField:j["a"]})},"8b37":function(t,e,s){}}]);
//# sourceMappingURL=chunk-0baf9879.10a3284b.js.map