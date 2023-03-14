import { LitElement, html, css} from 'lit';
import { pages } from 'primefaces-extensions-data';
import 'qwc/qwc-extension-link.js';

const NAME = "PrimeFaces Extensions";

export class QwcPrimeFacesExtensionsCard extends LitElement {

    static styles = css`
      .identity {
        display: flex;
        justify-content: flex-start;
      }

      .description {
        padding-bottom: 10px;
      }

      .logo {
        padding-bottom: 10px;
        margin-right: 5px;
      }

      .card-content {
        color: var(--lumo-contrast-90pct);
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        padding: 2px 2px;
        height: 100%;
      }

      .card-content slot {
        display: flex;
        flex-flow: column wrap;
        padding-top: 5px;
      }
    `;

    static properties = {
        description: {type: String}
    };

    constructor() {
        super();
    }

    connectedCallback() {
        super.connectedCallback();
    }

    render() {
        return html`<div class="card-content" slot="content">
            <div class="identity">
                <div class="logo">
                    <img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxzdmcgd2lkdGg9IjMwMHB4IiBoZWlnaHQ9IjMwMHB4IiB2aWV3Qm94PSIwIDAgMzUgNDAiIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+DQogICAgPCEtLSBHZW5lcmF0b3I6IFNrZXRjaCA2My4xICg5MjQ1MikgLSBodHRwczovL3NrZXRjaC5jb20gLS0+DQogICAgPHRpdGxlPmljb24tcHJpbWU8L3RpdGxlPg0KICAgIDxkZXNjPkNyZWF0ZWQgd2l0aCBTa2V0Y2guPC9kZXNjPg0KICAgIDxnIGlkPSJTeW1ib2xzIiBzdHJva2U9Im5vbmUiIHN0cm9rZS13aWR0aD0iMSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4NCiAgICAgICAgPGcgaWQ9InByaW1lL2ljb24iIHRyYW5zZm9ybT0idHJhbnNsYXRlKC0xMC4wMDAwMDAsIC04LjAwMDAwMCkiIGZpbGw9IiNFMEE3NzEiPg0KICAgICAgICAgICAgPGcgaWQ9InByaW1lLWljb24iPg0KICAgICAgICAgICAgICAgIDxnIGlkPSJpY29uLXByaW1lIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgxMC4yMDAwMDAsIDguMDAwMDAwKSI+DQogICAgICAgICAgICAgICAgICAgIDxwb2x5Z29uIGlkPSJjaGljay1yaWdodCIgZmlsbC1ydWxlPSJub256ZXJvIiBwb2ludHM9IjI1LjU3MzkxMzQgMTguMDQ1ODAxOCAyMi44NjYwODczIDE3LjQ0NDI3NTEgMjQuOTcyMTc0MyAyMC40NTE5MDg3IDI0Ljk3MjE3NDMgMjkuNzc1NTczIDMyLjE5MzA0NCAyMy43NjAzMDU3IDMyLjE5MzA0NCAxMy41MzQzNTEzIDI4Ljg4MzQ3ODcgMTQuNzM3NDA0OCI+PC9wb2x5Z29uPg0KICAgICAgICAgICAgICAgICAgICA8cG9seWdvbiBpZD0iY2hpY2stbGVmdCIgZmlsbC1ydWxlPSJub256ZXJvIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSg2Ljc2OTU2NSwgMjEuNjU0OTYyKSBzY2FsZSgtMSwgMSkgdHJhbnNsYXRlKC02Ljc2OTU2NSwgLTIxLjY1NDk2MikgIiBwb2ludHM9IjQuODEzOTEzMTIgMTguMDQ1ODAxOCAyLjEwNjA4Njk5IDE3LjQ0NDI3NTEgNC4yMTIxNzM5OCAyMC40NTE5MDg3IDQuMjEyMTczOTggMjkuNzc1NTczIDExLjQzMzA0MzYgMjMuNzYwMzA1NyAxMS40MzMwNDM2IDEzLjUzNDM1MTMgOC4xMjM0NzgzOCAxNC43Mzc0MDQ4Ij48L3BvbHlnb24+DQogICAgICAgICAgICAgICAgICAgIDxwYXRoIGQ9Ik0yNC4wNjk2NDQ1LDMxLjI3ODc0NzMgTDE5Ljg1NzUxODgsMzQuMjg3NTA0OSBMMTkuODU3MzEyNywzNS40OTA3MTkzIEwyNC4wNjk0Mzg0LDMyLjQ4MTk2MTggTDI0LjA2OTY0NDUsMzEuMjc4NzQ3MyBaIE0yNC4wNjk2NDQ1LDI4Ljg3MjY0MDQgTDE5Ljg1NzUxODgsMzEuODgxMzk4IEwxOS44NTczMTI3LDMzLjA4NDYxMjQgTDI0LjA2OTQzODQsMzAuMDc1ODU0OSBMMjQuMDY5NjQ0NSwyOC44NzI2NDA0IFogTTEwLjIyOTY5MjYsMjcuNjY5NzQ4IEwxNC40NDE4MTgyLDMwLjY3ODUwNTUgTDE0LjQ0MTYxMjEsMjkuNDc1MjkxIEwxMC4yMjk0ODY1LDI2LjQ2NjUzMzUgTDEwLjIyOTU2NTQsMjEuMDUzNDM1NCBMMTIuNjM2NTIxOSwxNy40NDQyNzUxIEwxNC4xNDA4Njk4LDE4LjM0NjU2NTIgTDIwLjE1ODI2MTIsMTguMzQ2NTY1MiBMMjEuNjYyNjA5LDE3LjQ0NDI3NTEgTDI0LjA2OTU2NTYsMjEuMDUzNDM1NCBMMjQuMDY5NTY1NiwzNC41ODc3ODY4IEwyMi4yNjQzNDgyLDM3LjI5NDY1NyBMMjAuMTU4MjYxMiwzOS40MDAwMDA2IEwxNC4xNDA4Njk4LDM5LjQwMDAwMDYgTDEyLjAzNDc4MjgsMzcuMjk0NjU3IEwxMC4yMjk1NjU0LDM0LjU4Nzc4NjggTDEwLjIyOTY5MjYsMzIuNDgxOTYxOCBMMTQuNDQxODE4MiwzNS40OTA3MTkzIEwxNC40NDE2MTIxLDM0LjI4NzUwNDkgTDEwLjIyOTQ4NjUsMzEuMjc4NzQ3MyBMMTAuMjI5NjkyNiwzMC4wNzU4NTQ5IEwxNC40NDE4MTgyLDMzLjA4NDYxMjQgTDE0LjQ0MTYxMjEsMzEuODgxMzk4IEwxMC4yMjk0ODY1LDI4Ljg3MjY0MDQgTDEwLjIyOTY5MjYsMjcuNjY5NzQ4IFogTTI0LjA2OTY0NDUsMjYuNDY2NTMzNSBMMTkuODU3NTE4OCwyOS40NzUyOTEgTDE5Ljg1NzMxMjcsMzAuNjc4NTA1NSBMMjQuMDY5NDM4NCwyNy42Njk3NDggTDI0LjA2OTY0NDUsMjYuNDY2NTMzNSBaIiBpZD0ibWFzayI+PC9wYXRoPg0KICAgICAgICAgICAgICAgICAgICA8cG9seWdvbiBpZD0iYm90dG9tLWNoaWNrLXJpZ2h0IiBmaWxsLXJ1bGU9Im5vbnplcm8iIHBvaW50cz0iMjQuOTcyMTc0MyAzNS40OTAwNzY5IDI4Ljg4MzQ3ODcgMzEuNTgwMTUzMSAyOC44ODM0Nzg3IDI3LjY3MDIyOTQgMjQuOTcyMTc0MyAzMC45Nzg2MjY0Ij48L3BvbHlnb24+DQogICAgICAgICAgICAgICAgICAgIDxwb2x5Z29uIGlkPSJib3R0b20tY2hpY2stbGVmdCIgZmlsbC1ydWxlPSJub256ZXJvIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSg3LjM3MTMwNCwgMzEuNTgwMTUzKSBzY2FsZSgtMSwgMSkgdHJhbnNsYXRlKC03LjM3MTMwNCwgLTMxLjU4MDE1MykgIiBwb2ludHM9IjUuNDE1NjUyMjUgMzUuNDkwMDc2OSA5LjMyNjk1NjY2IDMxLjU4MDE1MzEgOS4zMjY5NTY2NiAyNy42NzAyMjk0IDUuNDE1NjUyMjUgMzAuOTc4NjI2NCI+PC9wb2x5Z29uPg0KICAgICAgICAgICAgICAgICAgICA8cGF0aCBkPSJNMTguOTU0NzgyOSwzLjgxOTE2NzJlLTE0IEwxOC45NTQ3ODI5LDcuNTE5MDg0MDggTDIwLjE1ODI2MTIsNi45MTc1NTczNSBMMjAuMTU4MjYxMiwzLjgxOTE2NzJlLTE0IEwyMS4wNjA4Njk5LDAgTDIzLjQ2NzgyNjQsNS43MTQ1MDM5IEwxOS44NTczOTE2LDE3LjE0MzUxMTcgTDE3Ljc1MTMwNDYsMTcuMTQzNTExNyBMMTcuNzUxMzA0NiwwIEwxOC45NTQ3ODI5LDMuODE5MTY3MmUtMTQgWiBNMTQuMTQwODY5OCw2LjkxNzU1NzM1IEwxNS4zNDQzNDgxLDcuNTE5MDg0MDggTDE1LjM0NDM0ODEsMCBMMTYuNTQ3ODI2MywwIEwxNi41NDc4MjYzLDE3LjE0MzUxMTcgTDE0Ljc0MjYwODksMTcuMTQzNTExNyBMMTAuODMxMzA0NSw1LjcxNDUwMzkgTDEzLjIzODI2MTEsMCBMMTQuMTQwODY5OCwwIEwxNC4xNDA4Njk4LDYuOTE3NTU3MzUgWiIgaWQ9ImhlYWQiPjwvcGF0aD4NCiAgICAgICAgICAgICAgICAgICAgPHBvbHlnb24gaWQ9ImhlYWQtbGVmdCIgZmlsbC1ydWxlPSJub256ZXJvIiBwb2ludHM9IjE0Ljc0MjYwODkgMTcuMTQzNTExNyAxLjgwNTIxNzQyIDEyLjMzMTI5NzkgMCA0LjgxMjIxMzgxIDExLjEzMjE3NDEgNS43MTQ1MDM5IDE1LjA0MzQ3ODUgMTcuMTQzNTExNyI+PC9wb2x5Z29uPg0KICAgICAgICAgICAgICAgICAgICA8cG9seWdvbiBpZD0iaGVhZC1yaWdodCIgZmlsbC1ydWxlPSJub256ZXJvIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgyNy4wNzgyNjEsIDEwLjk3Nzg2Mykgc2NhbGUoLTEsIDEpIHRyYW5zbGF0ZSgtMjcuMDc4MjYxLCAtMTAuOTc3ODYzKSAiIHBvaW50cz0iMzQuMjk5MTMwOSAxNy4xNDM1MTE3IDIxLjM2MTczOTQgMTIuMzMxMjk3OSAxOS41NTY1MjIgNC44MTIyMTM4MSAzMC45ODk1NjU3IDUuNzE0NTAzOSAzNC42MDAwMDA1IDE3LjE0MzUxMTciPjwvcG9seWdvbj4NCiAgICAgICAgICAgICAgICAgICAgPHBvbHlnb24gaWQ9ImVhci1yaWdodCIgZmlsbC1ydWxlPSJub256ZXJvIiBwb2ludHM9IjI0LjA2OTU2NTYgNC44MTIyMTM4MSAzMC4zODc4MjY1IDQuMjEwNjg3MDkgMjYuMTc1NjUyNiAwIDIxLjk2MzQ3ODYgMCI+PC9wb2x5Z29uPg0KICAgICAgICAgICAgICAgICAgICA8cG9seWdvbiBpZD0iZWFyLWxlZnQiIGZpbGwtcnVsZT0ibm9uemVybyIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoOC4xMjM0NzgsIDIuNDA2MTA3KSBzY2FsZSgtMSwgMSkgdHJhbnNsYXRlKC04LjEyMzQ3OCwgLTIuNDA2MTA3KSAiIHBvaW50cz0iNi4wMTczOTEzOSA0LjgxMjIxMzgxIDEyLjMzNTY1MjQgNC4yMTA2ODcwOSA4LjEyMzQ3ODM4IDAgMy45MTEzMDQ0MSAwIj48L3BvbHlnb24+DQogICAgICAgICAgICAgICAgPC9nPg0KICAgICAgICAgICAgPC9nPg0KICAgICAgICA8L2c+DQogICAgPC9nPg0KPC9zdmc+"
                                       alt="${NAME}" 
                                       title="${NAME}"
                                       width="32" 
                                       height="32">
                </div>
                <div class="description">${this.description}</div>
            </div>
            ${this._renderCardLinks()}
        </div>
        `;
    }

    _renderCardLinks(){
        return html`${pages.map(page => html`
                            <qwc-extension-link slot="link"
                                extensionName="${NAME}"
                                iconName="${page.icon}"
                                displayName="${page.title}"
                                staticLabel="${page.staticLabel}"
                                dynamicLabel="${page.dynamicLabel}"
                                streamingLabel="${page.streamingLabel}"
                                path="${page.id}" 
                                webcomponent="${page.componentLink}" >
                            </qwc-extension-link>
                        `)}`;
    }

}
customElements.define('qwc-primefaces-extensions-card', QwcPrimeFacesExtensionsCard);