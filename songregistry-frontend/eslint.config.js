import globals from "globals";
import tseslint from "typescript-eslint";
import pluginJs from "@eslint/js";

/** @type {import('eslint').Linter.Config[]} */
export default [
  {
    files: ["**/*.{js,mjs,cjs,ts,jsx,tsx}"],
    languageOptions: {
      globals: globals.browser,
    },
    rules: {
      ...pluginJs.configs.recommended.rules,
      ...tseslint.configs.recommended.rules,
    },
  },
  {
    // Ignore Cypress test files and other specified files
    ignores: [
      "*.config.js",  // Ignore config files
      "**/*cy.js",    // Ignore Cypress test files
      "**/*.spec.js", // Ignore test files
      "**/*.spec.ts", // Ignore TypeScript test files
      "**/*.cy.ts",   // Ignore Cypress test files in TypeScript
    ],
  },
];
