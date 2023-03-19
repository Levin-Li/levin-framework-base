amis.define('route/Preview.tsx', function(require, exports, module, define) {

  "use strict";
  Object.defineProperty(exports, "__esModule", { value: true });
  var tslib_1 = require("node_modules/tslib/tslib");
  var react_1 = tslib_1.__importDefault(require("node_modules/react/index"));
  var mobx_react_1 = require("node_modules/mobx-react/dist/index");
  var amis_1 = require("node_modules/amis/lib/index");
  var react_router_1 = require("node_modules/react-router/index");
  var react_router_dom_1 = require("node_modules/react-router-dom/index");
  var NotFound_1 = tslib_1.__importDefault(require("route/NotFound.tsx"));
  var AMISRenderer_1 = tslib_1.__importDefault(require("component/AMISRenderer.tsx"));
  // import AddPageModal from '../component/AddPageModal';
  function isActive(link, location) {
      var ret = (0, react_router_1.matchPath)(location.pathname, {
          path: link ? link.replace(/\?.*$/, '') : '',
          exact: true,
          strict: true
      });
      return !!ret;
  }
  exports.default = (0, mobx_react_1.inject)('store')((0, mobx_react_1.observer)(function (_a) {
      var store = _a.store, location = _a.location, history = _a.history;
      function renderHeader() {
          return (react_1.default.createElement("div", null,
              react_1.default.createElement("div", { className: "a-Layout-headerBar" },
                  react_1.default.createElement("div", { className: "hidden-xs p-t-sm pull-right px-2" },
                      react_1.default.createElement(amis_1.Button, { size: "sm", className: "m-r-xs", level: "success", disabled: true }, "\u5168\u90E8\u5BFC\u51FA"),
                      react_1.default.createElement(amis_1.Button, { size: "sm", level: "info", onClick: function () { return store.setAddPageIsOpen(true); } }, "\u65B0\u589E\u9875\u9762")))));
      }
      function renderAside() {
          var navigations = store.pages.map(function (item) { return ({
              label: item.label,
              path: "/".concat(item.path),
              icon: item.icon
          }); });
          var paths = navigations.map(function (item) { return item.path; });
          return (react_1.default.createElement(amis_1.AsideNav, { key: store.asideFolded ? 'folded-aside' : 'aside', navigations: [
                  {
                      label: '导航',
                      children: navigations
                  }
              ], renderLink: function (_a) {
                  var link = _a.link, toggleExpand = _a.toggleExpand, cx = _a.classnames, depth = _a.depth;
                  if (link.hidden) {
                      return null;
                  }
                  var children = [];
                  if (link.children) {
                      children.push(react_1.default.createElement("span", { key: "expand-toggle", className: cx('AsideNav-itemArrow'), onClick: function (e) { return toggleExpand(link, e); } }));
                  }
                  link.badge &&
                      children.push(react_1.default.createElement("b", { key: "badge", className: cx("AsideNav-itemBadge", link.badgeClassName || 'bg-info') }, link.badge));
                  if (link.icon) {
                      children.push(react_1.default.createElement("i", { key: "icon", className: cx("AsideNav-itemIcon", link.icon) }));
                  }
                  else if (store.asideFolded && depth === 1) {
                      children.push(react_1.default.createElement("i", { key: "icon", className: cx("AsideNav-itemIcon", link.children ? 'fa fa-folder' : 'fa fa-info') }));
                  }
                  link.active ||
                      children.push(react_1.default.createElement("i", { key: "delete", "data-tooltip": "\u5220\u9664", "data-position": "bottom", className: 'navbtn fa fa-times', onClick: function (e) {
                              e.preventDefault();
                              (0, amis_1.confirm)('确认要删除').then(function (confirmed) {
                                  confirmed && store.removePageAt(paths.indexOf(link.path));
                              });
                          } }));
                  children.push(react_1.default.createElement("i", { key: "edit", "data-tooltip": "\u7F16\u8F91", "data-position": "bottom", className: 'navbtn fa fa-pencil', onClick: function (e) {
                          e.preventDefault();
                          history.push("/edit/".concat(paths.indexOf(link.path)));
                      } }));
                  children.push(react_1.default.createElement("span", { key: "label", className: cx('AsideNav-itemLabel') }, link.label));
                  return link.path ? (link.active ? (react_1.default.createElement("a", null, children)) : (react_1.default.createElement(react_router_dom_1.Link, { to: link.path[0] === '/' ? link.path : "".concat(link.path) }, children))) : (react_1.default.createElement("a", { onClick: link.onClick
                          ? link.onClick
                          : link.children
                              ? function () { return toggleExpand(link); }
                              : undefined }, children));
              }, isActive: function (link) {
                  return isActive(link.path && link.path[0] === '/' ? link.path : "".concat(link.path), location);
              } }));
      }
      function handleConfirm(value) {
          store.addPage(tslib_1.__assign(tslib_1.__assign({}, value), { schema: {
                  type: 'page',
                  title: value.label,
                  body: '这是你刚刚新增的页面。'
              } }));
          store.setAddPageIsOpen(false);
      }
      return (react_1.default.createElement(amis_1.Layout, { aside: renderAside(), header: renderHeader(), folded: store.asideFolded, offScreen: store.offScreen },
          react_1.default.createElement(react_router_1.Switch, null,
              store.pages.map(function (item) { return (react_1.default.createElement(react_router_1.Route, { key: item.id, path: "/".concat(item.path), render: function () { return react_1.default.createElement(AMISRenderer_1.default, { schema: item.schema }); } })); }),
              react_1.default.createElement(react_router_1.Route, { component: NotFound_1.default }))));
  }));
  

});