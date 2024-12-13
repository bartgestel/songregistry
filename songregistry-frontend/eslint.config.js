import globals from "globals";
import tseslint from "@typescript-eslint/eslint-plugin";
import tsParser from "@typescript-eslint/parser";
import pluginJs from "@eslint/js";
import reactPlugin from "eslint-plugin-react";

/** @type {import('eslint').Linter.Config[]} */
export default [
  {
    ignores: ["src/components/ui/**"], // Ensure this is at the top level
  },
  {
    files: ["**/*.{js,mjs,cjs,ts,jsx,tsx}"],
    languageOptions: {
      parser: tsParser,
      globals: globals.browser,
      parserOptions: {
        ecmaFeatures: {
          jsx: true,
        },
      },
    },
    plugins: {
      "@typescript-eslint": tseslint,
      react: reactPlugin,
    },
    rules: {
      ...pluginJs.configs.recommended.rules,
      ...tseslint.configs.recommended.rules,
      ...reactPlugin.configs.recommended.rules,
      "react/react-in-jsx-scope": "off", // Disable the rule
    },
    settings: {
      react: {
        version: "detect",
      },
    },
  },
  {
    // Ignore Cypress test files and other specified files
    ignores: [
      "*.config.js",  // Ignore config files
        "*.config.ts",  // Ignore config files
      "*.config.cjs",  // Ignore config files
      "**/*cy.js",    // Ignore Cypress test files
      "**/*.spec.js", // Ignore test files
      "**/*.spec.ts", // Ignore TypeScript test files
      "**/*.cy.ts",   // Ignore Cypress test files in TypeScript
    ],
  },
];