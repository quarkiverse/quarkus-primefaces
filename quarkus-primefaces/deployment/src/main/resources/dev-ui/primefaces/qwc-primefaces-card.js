import { LitElement, html, css} from 'lit';
import { pages } from 'primefaces-data';
import 'qwc/qwc-extension-link.js';

const NAME = "PrimeFaces";

export class QwcPrimeFacesCard extends LitElement {

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
                    <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAD3ElEQVR4AaWXA5DsWBiFV4W1y2vbHj7btm3btm3btm3btv3m7Dmpur2zqXQwqaqv+ePr2/lvkmcAuPLZT3+0JAjIU5JX+V54Na9CkEIekcy+BBgYT96wNc9Hnv4SnxajJs/AjAXLLEpVqRl5nSF3wcjr0lVr8Xmp9Xrc9Dn4M3VGSdwjiba675K/7QLbCMhxMp10Jo/UfNaiFdix/4iwCg8bPyXyPkfhEv99N202ho+fGnm/cMVaI3GTdCRzyFkCMsku0Ma+hPbmolLdxti+77CjgKhYu4GenSTsFLYLxBB88ctfaNW5B+o2b4uq9Zugaj1SvylqNG7Bz9qgbbc+KhxVoHOfgajRqAWqN2yGypStVKehJVW7aSu06NQdcRmzq/lj8oZd4HlylZhf7cjmXftdBZat26znqPwUm1oCS5ymQBLjCTr07O9WxFVg656DUWNnL15hlr9KNIFiBCUq1VCC5yrYBbbtPeSa16n3ACPwfjSBd0nSb4npUySwatN21zz9ME2b20YkiY0E3foPNY1c8LcCmpoxU2dpqiTQzEugBbGO3HI16qIKp2DCzHmwF924c19EwDSxxyzgCDZu04nT0IhT0dws/w9eAlkIegwcbhVZuXEbUmfLg5gM2dCkXWcsXrX+f3+DEVi7dZf1vI7PXfoOQpb8RRHLnKVrN1mfDx4zUc0fkOe9BD5OLiBW87+VhD7/5o9Y5Cpa2io4bd5ipM2RD+O5O06ZsxAlK9fED/8kKs5qvmxtZCSNwA7Pk5EMZWoEDGu37MT85Wu439fGIq6Cjmgtr6F9j75YumYjileqoaXXyinPLjDWS8BI7LILCB2Y9s/tDBgxjvtIP712EmjmV2CSUyOd7fjsSdnqdaIJ5PIr0NJJoEKt+r4EGBdN4Eu/AoUKlq6ITRq18AKaGB4b1Z+w7gt+BX4iSJ8rv47kUAIrN2xF5ryF9ev3qrZfgZcIxF9pM2Py7AUpEtDVUUz6rKojJvsVMBInCDLRPnexMugzZFQggQEjxyFnkVLKNwItggosMAKNOOfFKlZH1gJF/QgwrhiKlKuivOQCBYIKJJBH2oIbtu6oYtoBfQkoTvEiPlMONd9JXgkiYCTKEqTOnlcSgQV0xcz8S+SDMPcFXY0EC7pe8ZizIpfdNH9EYsLemDxP5hKI+i3buwo079ANn//8p2JFqbB3RkbiNbKX4Mtf/8bQcZMdm+ui4+vfY0zzriY/rICR+IhcIfjqt3904artNULXfoPx7Z+xpvl88nxwAW+JGPKIwIX95HXFhxJwkSjh0vwa+Uxx4QXcJX4nCQ58ErSW+BeIv/QIbbwjLAAAAABJRU5ErkJggg=="
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
customElements.define('qwc-primefaces-card', QwcPrimeFacesCard);