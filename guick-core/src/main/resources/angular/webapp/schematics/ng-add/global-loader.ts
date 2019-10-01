import { Tree } from '@angular-devkit/schematics';
import { getWorkspace } from '@schematics/angular/utility/config';
import { getProjectFromWorkspace } from '@angular/cdk/schematics';
import { Schema } from './schema';
import { getIndexHtmlPath, appendHtmlElement } from '../utils';

/** Adds the Material Design fonts to the index HTML file. */
export function addLoaderToIndex(options: Schema): (host: Tree) => Tree {
  return (host: Tree) => {
    const workspace = getWorkspace(host);
    const project = getProjectFromWorkspace(workspace, options.project);
    const projectIndexHtmlPath = getIndexHtmlPath(project);

    const loaderStyles = `
    .global-loader {
      display: flex;
      justify-content: center;
      align-items: center;
      position: fixed;
      top: 0;
      left: 0;
      z-index: 1999;
      width: 100%;
      height: 100%;
      background-color: #fff;
      opacity: 1;
      transition: opacity .5s ease-in-out;
    }

    .global-loader-fade-in {
      opacity: 0;
    }

    .global-loader-hidden {
      display: none;
    }

    .global-loader h1 {
      font-family: "Helvetica Neue", Helvetica, sans-serif;
      font-weight: normal;
      font-size: 24px;
      letter-spacing: 0.04rem;
      white-space: pre;

      -webkit-background-clip: text;
      background-clip: text;
      -webkit-text-fill-color: transparent;
      background-image: repeating-linear-gradient(to right,
          #F44336, #9C27B0, #3F51B5, #03A9F4, #009688, #8BC34A, #FFEB3B, #FF9800);
      background-size: 750% auto;
      background-position: 0 100%;
      animation: gradient 20s infinite;
      animation-fill-mode: forwards;
      animation-timing-function: linear;
    }

    @keyframes gradient {
      0% {
        background-position: 0 0;
      }

      100% {
        background-position: -750% 0;
      }
    }
    `;

    const loaderHtml = `<div id="globalLoader" class="global-loader"><h1>LOADING</h1></div>`;

    appendHtmlElement(
      host,
      projectIndexHtmlPath,
      `<style type="text/css">${loaderStyles}</style>`,
      'head'
    );

    appendHtmlElement(host, projectIndexHtmlPath, loaderHtml, 'body');

    return host;
  };
}
