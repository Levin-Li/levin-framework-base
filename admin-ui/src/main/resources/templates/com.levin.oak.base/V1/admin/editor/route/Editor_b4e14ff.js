amis.define('route/Editor.tsx', function(require, exports, module, define) {

  "use strict";
  Object.defineProperty(exports, "__esModule", { value: true });
  var tslib_1 = require("node_modules/tslib/tslib");
  var react_1 = tslib_1.__importDefault(require("node_modules/react/index"));
  var amis_editor_1 = require("node_modules/amis-editor/lib/index");
  var mobx_react_1 = require("node_modules/mobx-react/dist/index");
  var amis_1 = require("node_modules/amis/lib/index");
  var index_1 = require("icons/index.tsx");
  require("plugin/DisabledEditorPlugin.tsx"); // 用于隐藏一些不需要的Editor预置组件
  var SchemaApi_1 = require("api/SchemaApi.ts");
  // @ts-ignore
  '../schema.json';
  var iframeUrl = 'editor.html';
  var schemaUrl = 'schema.json';
  exports.default = (0, mobx_react_1.inject)('store')((0, mobx_react_1.observer)(function (_a) {
      var store = _a.store, location = _a.location, history = _a.history, match = _a.match;
      function load() {
          (0, amis_1.confirm)("重新加载将导致变更丢失，确定要重新加载？", "加载提示", "确定")
              .then(function (ok) {
              if (ok) {
                  (0, SchemaApi_1.loadSchema)(function (schema) {
                      store.updateSchema(schema);
                      amis_1.toast.info("页面已加载", '系统消息');
                  }, store);
              }
          });
      }
      function save() {
          (0, amis_1.confirm)("确定要保存？", "保存提示", "确定")
              .then(function (ok) {
              if (ok) {
                  (0, SchemaApi_1.saveSchema)(store.schema, store);
              }
          });
      }
      return (react_1.default.createElement("div", { className: "Editor-Base" },
          react_1.default.createElement("div", { className: "Editor-header" },
              react_1.default.createElement("div", { className: "Editor-title" }, store.title || '可视化页面编辑器'),
              react_1.default.createElement("div", { className: "Editor-view-mode-group-container" },
                  react_1.default.createElement("div", { className: "Editor-view-mode-group" },
                      react_1.default.createElement("div", { className: "Editor-view-mode-btn ".concat(!store.isMobile ? 'is-active' : ''), onClick: function () {
                              store.setIsMobile(false);
                          } },
                          react_1.default.createElement(index_1.Icon, { icon: "pc-preview", title: "PC\u6A21\u5F0F" })),
                      react_1.default.createElement("div", { className: "Editor-view-mode-btn ".concat(store.isMobile ? 'is-active' : ''), onClick: function () {
                              store.setIsMobile(true);
                          } },
                          react_1.default.createElement(index_1.Icon, { icon: "h5-preview", title: "\u79FB\u52A8\u6A21\u5F0F" })))),
              react_1.default.createElement("div", { className: "Editor-header-actions" },
                  react_1.default.createElement("div", { className: "header-action-btn margin-left-space ".concat(store.preview ? 'primary' : ''), onClick: function () {
                          store.setPreview(!store.preview);
                      } }, store.preview ? '返回编辑' : '预览'),
                  !store.preview && (react_1.default.createElement("div", null,
                      react_1.default.createElement("div", { className: "header-action-btn exit-btn", onClick: load }, "\u91CD\u65B0\u52A0\u8F7D"),
                      react_1.default.createElement("div", { className: "header-action-btn exit-btn", onClick: save }, "\u4FDD\u5B58"))))),
          react_1.default.createElement("div", { className: "Editor-inner" },
              react_1.default.createElement(amis_editor_1.Editor, { theme: store.theme, preview: store.preview, isMobile: store.isMobile, value: store.schema, onChange: function (value) { return store.updateSchema(value); }, onPreview: function () {
                      store.setPreview(true);
                  }, onSave: save, className: "is-fixed", "$schemaUrl": schemaUrl, iframeUrl: iframeUrl, showCustomRenderersPanel: true }))));
  }));
  

});
