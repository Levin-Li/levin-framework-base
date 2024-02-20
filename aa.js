 function (e) {
    var t, n = e.preset, o = e.redirectToLogin, i = e.shouldRedirectToLogin, a = void 0 === i ? X : i,
        s = e.isCrawlerInfoRequired, c = e.getCrawlerInfo, u = void 0 === c ? $ : c, l = e.skipReportError,
        d = void 0 !== l && l, f = e.crawlerOptions, A = e.shouldForceReject, p = void 0 !== A && A;
    return function (e) {
        return {
            beforeSend: function (n) {
                try {
                    var r = n.input
                        , o = n.init
                        , i = void 0 === o ? {} : o;
                    t = i;
                    var a = r;
                    return !L.zT || s && s(r) ? Promise.resolve(u(e.rawFetch, f).catch((function () {
                        }
                    ))).then((function (e) {
                            if (s && s(r))
                                try {
                                    var t,
                                        o = null === i || void 0 === i || null === (t = i.method) || void 0 === t ? void 0 : t.toUpperCase();
                                    o === G.Get ? a = function (e, t) {
                                        if (!t)
                                            return e;
                                        var n = -1 !== e.indexOf("?") ? "&" : "?";
                                        if ("string" === typeof t)
                                            return "" + e + n + t;
                                        var r = [];
                                        return Object.keys(t || {}).forEach((function (e) {
                                                void 0 !== t[e] && r.push(e + "=" + encodeURIComponent(t[e]))
                                            }
                                        )),
                                        "" + e + n + r.join("&")
                                    }(r, {
                                        crawlerInfo: e
                                    }) : o === G.Post && (i.body = JSON.stringify(U({}, JSON.parse(i.body), {
                                        crawlerInfo: e
                                    })))
                                } catch (u) {
                                    console.log(u)
                                }
                            return (c = function (e) {
                                if (!W(e))
                                    return e;
                                var t = e.replace(/^\//, "");
                                return t === e && console.warn("Please use absolute path like /xxx or //xxx.com/xxx. Current url is: " + e),
                                "/" + t
                            }(r)) || (c = ""),
                                W(c) || Q.test(c) || z.test(c) || V.test(c) ? U({}, n, {
                                    input: a,
                                    init: U({}, i, {
                                        headers: U({}, null === i || void 0 === i ? void 0 : i.headers, {
                                            "Anti-Content": e || "getRisckInfoError"
                                        })
                                    })
                                }) : U({}, n, {
                                    input: a
                                });
                            var c
                        }
                    )) : Promise.resolve(U({}, n, {
                        input: a
                    }))
                } catch (c) {
                    return Promise.reject(c)
                }
            },
            onResponse: function (i) {
                return new Promise((function (s, c) {
                        var u, l = i.res, f = i.data,
                            A = null === l || void 0 === l || null === (u = l.headers) || void 0 === u ? void 0 : u.get("checklogin"),
                            h = a(), v = t || {}, m = v.redirectOnExpired, g = void 0 === m || m, y = v.skipHandleJson,
                            b = void 0 !== y && y;
                        if ("mobile" === n) {
                            if (f.errorCode === K && g && h)
                                return L.zT || L.qe ? c({
                                    res: l,
                                    data: {
                                        url: l && l.url ? l.url : "",
                                        errorCode: f.error_code || f.errorCode,
                                        error_code: f.error_code || f.errorCode,
                                        errorMsg: f.error_msg || f.errorMsg,
                                        error_msg: f.error_msg || f.errorMsg
                                    }
                                }) : o();
                            if ((0,
                                r.BZ)(f, "success") && !0 === f.success)
                                return e.skipReportError = d,
                                    s(i);
                            if ((0,
                                r.BZ)(f, "success") && !0 !== f.success) {
                                var x = {
                                    url: l && l.url ? l.url : "",
                                    errorCode: f.error_code || f.errorCode,
                                    error_code: f.error_code || f.errorCode,
                                    errorMsg: f.error_msg || f.errorMsg,
                                    error_msg: f.error_msg || f.errorMsg
                                };
                                return c({
                                    res: l,
                                    data: Object.assign(x, f)
                                })
                            }
                            if ((0,
                                r.BZ)(f, "result", "data"))
                                return s(i);
                            if ((0,
                                r.BZ)(f, "error_msg", "error_code") || (0,
                                r.BZ)(f, "errorMsg", "errorCode")) {
                                var w = {
                                    url: l && l.url ? l.url : "",
                                    errorCode: f.error_code || f.errorCode,
                                    error_code: f.error_code || f.errorCode,
                                    errorMsg: f.error_msg || f.errorMsg,
                                    error_msg: f.error_msg || f.errorMsg
                                };
                                return c({
                                    res: l,
                                    data: Object.assign(w, f)
                                })
                            }
                            return s({
                                res: l,
                                data: {
                                    result: f
                                }
                            })
                        }
                        if (!l || !f)
                            return c(i);
                        if ("mms" === n) {
                            var C = (t || {}).redirectOnExpired
                                , S = void 0 !== C && C;
                            if (f.errorCode === K && A === q && S)
                                return p ? (o(),
                                    c(i)) : o()
                        } else if (f.errorCode === K && A === q && g && h)
                            return p ? (o(),
                                c(i)) : o();
                        return 403 === l.status && 31012e4 === f.errorCode ? window.location.reload() : (429 === l.status || 403 === l.status) && 40001 === f.errorCode && g && h ? (e.skipReportError = d,
                            p ? (o(),
                                c(i)) : o()) : (0,
                            r.BZ)(f, "success") && !1 === f.success ? (e.skipReportError = d,
                            b ? s(i) : c(i)) : Number(l.status) >= 200 && Number(l.status) < 400 ? s(i) : c(i)
                    }
                ))
            },
            DEBUG_NAME: "fetch-plugin-risk-status"
        }
    }
}