import { createGlobalStyle } from "styled-components";

export const GlobalStyled = createGlobalStyle`
/*Reset*/
/* 17a1f1 */
:root {
  --main: #57c90c;
  --dark: #151515;
  --light: #fff;
}
::selection {
  /* background-color: red;
  color: green; */
}
html {
  box-sizing: border-box;
  scroll-behavior: smooth;
  cursor: default;
  /* background: url("/bg.webp") no-repeat center center; */
  /* background-size: cover; */
  font-family: 'Epilogue', sans-serif;
}

*,
*::before,
*::after {
  box-sizing: inherit;
}
body {
  min-height: 100vh;
  text-rendering: optimizeSpeed;
  line-height: 1.5;
  padding: 0;
  margin: 0;
  position: relative;
}
h1,
h2,
h3,
h4,
h5,
h6,
ul,
ol,
li,
p,
pre,
blockquote,
figure,
figcaption,
hr,
dl,
dd {
  margin: 0;
  padding: 0;
}

ul,
ol {
  list-style: none;
}

input,
textarea,
select,
button {
  color: inherit;
  font: inherit;
  letter-spacing: inherit;
}

input[type="text"],
textarea {
  width: 100%;
}

input,
textarea,
button {
  border: 1px solid gray;
}

button {
  padding: 0;
  line-height: inherit;
  border-radius: 0;
  background-color: transparent;
  cursor: pointer;
}

img,
iframe,
video,
object,
embed {
  display: block;
  max-width: 100%;
}

svg {
  max-width: 100%;
}

table {
  table-layout: fixed;
  width: 100%;
}

[hidden] {
  opacity: 0;
  visibility: hidden;
}

noscript {
  display: block;
  margin-bottom: 1em;
  margin-top: 1em;
}

[tabindex="-1"] {
  outline: none !important;
}

@media (prefers-reduced-motion: reduce) {
  * {
    animation-duration: 0.01ms !important;
    animation-iteration-count: 1 !important;
    transition-duration: 0.01ms !important;
    scroll-behavior: auto !important;
  }
}

.sr-only {
  position: absolute;
  width: 1px;
  height: auto;
  margin: 0;
  padding: 0;
  border: 0;
  clip: rect(0 0 0 0);
  overflow: hidden;
  white-space: nowrap;
}

* {
  text-decoration: none;
  color: inherit;
  box-sizing: border-box;
}

h1,
h2,
h3,
h4,
h5,
h6,
p,
span {
  font-size: inherit;
  font-style: inherit;
  font-weight: inherit;
  line-height: inherit;
}

img {
  display: block;
  max-width: 100%;
}

a {
  margin: 0px;
  padding: 0px;
  display: block;
}

table {
  border-spacing: 0px;
}

button {
  border: none;
}
hr {
  border: none;
}

a,
button,
svg path {
  transition: 0.3s;
}
#root {
 display: flex;
 flex-direction: column;
 align-items: center;
}

.btn {
  background-color: var(--light);
  box-shadow: 0 0 10px 30px var(--main) inset;
  color: var(--light);
  padding: 8px 20px;
  font-size: 18px;
  border-radius: 10px;
  max-width: fit-content;

  &:hover {
  box-shadow: 0 0 0 2px var(--main) inset;
  color: var(--main);
  }
}

.btn-danger {
  background-color: var(--light);
  box-shadow: 0 0 10px 30px #f30a0a inset;
  color: var(--light);
  padding: 8px 20px;
  font-size: 18px;
  border-radius: 10px;
  max-width: fit-content;

  &:hover {
  box-shadow: 0 0 0 2px #f30a0a inset;
  color: #f30a0a;
  }
}

.modal {
        position: fixed;
        top: 0;
        left: 0;
        z-index: 5;
        width: 100%;
        height: 100%;
        background-color: #0000002b;
        backdrop-filter: blur(2px);
        display: grid;
        justify-items: center;
        align-items: center;
        form {
            display: grid;
            justify-items: center;
            gap: 5px;
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background-color: var(--light);
            box-shadow: 0 4px 10px #0000004c;
            border-radius: 10px;

            h2 {
                font-size: 1.7rem;
                font-weight: 600;
                color: var(--main);
            }
            input + label {
                margin-top: 10px;
            }
            label {
                font-size: 20px;
                margin-right: auto;
                margin-left: 32px;
            }

            input {
                border: none;
                box-shadow: 0 2px 4px #00000053;
                border-radius: 10px;
                padding: 5px;
                max-width: 300px;
                width: 100%;
                outline: none;
            }
            select {
                width: 100%;
                max-width: 300px;
                padding: 7px 5px;
                border: none;
                box-shadow: 0 2px 4px #00000053;
                border-radius: 10px;
                outline: none;
                margin-bottom: 10px;
            }
            .guardabtn {
                margin-top: 10px;
                display: flex;
                gap: 15px;
            }
        }
        .guardabtn {
                margin-top: 10px;
                display: flex;
                gap: 15px;
            }
    }

`;