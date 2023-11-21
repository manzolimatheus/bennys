class CustomModal extends HTMLElement {

    id
    title
    visible = false

    static get observedAttributes() {
        return ['visible']
    }

    constructor() {
        super();
        this.id = `modal-${Math.floor(Math.random() * 1000)}`
        this.title = this.getAttribute('title')
        this.attachShadow({mode: 'open'})
    }

    toggleModal() {
        this.shadowRoot.querySelector(`#${this.id}`).classList.toggle('hidden')
    }

    connectedCallback() {
        this.shadowRoot.innerHTML = `
            <style>

                .backdrop {
                    position: fixed;
                    height: 100vh;
                    width: 100vw;
                    top: 0;
                    left: 0;
                    background-color: rgba(0, 0, 0, 0.5);
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    z-index: 10;
                }

                .card {
                    background-color: white;
                    padding: 1rem 2rem;
                    border-radius: 10px;
                    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
                    width: 50%;
                }

                .title {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }

                .title button {
                    background-color: transparent;
                    border: none;
                    cursor: pointer;
                    font: inherit;
                }

                .hidden {
                    display: none;
                }

            </style>
                <div
            class="${this.visible ? "backdrop" : "backdrop hidden"}" id="${this.id}">
            <div class="card">
                <div class="title">
                    <h2 class="text-2xl font-bold">${this.title}</h2>
                    <button id="close-button">Fechar</button>
                </div>
                <slot></slot>
            </div>
            </div>
        `

        this.shadowRoot.querySelector('#close-button').addEventListener('click', () => {
            this.toggleModal()
        })
    }
}

export default CustomModal;