amis.require.resourceMap({
  "res": {
    "loadMonacoEditor.ts": {
      "url": "loadMonacoEditor_6a700f0.js",
      "type": "js"
    },
    "route/NotFound.tsx": {
      "url": "route/NotFound_17fe1bf.js",
      "type": "js"
    },
    "component/AMISRenderer.tsx": {
      "url": "component/AMISRenderer_37dd514.js",
      "type": "js"
    },
    "component/AddPageModal.tsx": {
      "url": "component/AddPageModal_ebbaffb.js",
      "type": "js",
      "deps": [
        "component/AMISRenderer.tsx"
      ]
    },
    "route/Preview.tsx": {
      "url": "route/Preview_95c18e0.js",
      "type": "js",
      "deps": [
        "route/NotFound.tsx",
        "component/AMISRenderer.tsx",
        "component/AddPageModal.tsx"
      ]
    },
    "node_modules/amis-editor/dist/index.min": {
      "url": "node_modules/amis-editor/dist/index.min.js",
      "type": "js"
    },
    "icons/pc-preview.svg": {
      "url": "icons/pc-preview_a984475.js",
      "type": "js"
    },
    "icons/h5-preview.svg": {
      "url": "icons/h5-preview_6792d8a.js",
      "type": "js"
    },
    "icons/index.tsx": {
      "url": "icons/index_88b0047.js",
      "type": "js",
      "deps": [
        "icons/pc-preview.svg",
        "icons/h5-preview.svg"
      ]
    },
    "editor/DisabledEditorPlugin.tsx": {
      "url": "editor/DisabledEditorPlugin_03e2ef5.js",
      "type": "js",
      "deps": [
        "node_modules/amis-editor/dist/index.min"
      ]
    },
    "renderer/MyRenderer.tsx": {
      "url": "renderer/MyRenderer_957cfb1.js",
      "type": "js"
    },
    "editor/MyRenderer.tsx": {
      "url": "editor/MyRenderer_858428e.js",
      "type": "js",
      "deps": [
        "node_modules/amis-editor/dist/index.min"
      ]
    },
    "route/Editor.tsx": {
      "url": "route/Editor_3669740.js",
      "type": "js",
      "deps": [
        "node_modules/amis-editor/dist/index.min",
        "icons/index.tsx",
        "editor/DisabledEditorPlugin.tsx",
        "renderer/MyRenderer.tsx",
        "editor/MyRenderer.tsx"
      ]
    }
  },
  "pkg": {}
});