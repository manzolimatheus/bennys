class AnimalForm extends HTMLElement {

  id
  nome
  dono
  especie
  raca
  pelagem
  isEditing

  constructor() {
    super();
    this.attachShadow({ mode: 'open' })
    this.nome = this.getAttribute('nome') || ''
    this.dono = this.getAttribute('dono') || ''
    this.especie = this.getAttribute('especie') || ''
    this.raca = this.getAttribute('raca') || ''
    this.pelagem = this.getAttribute('pelagem') || ''
    this.id = this.getAttribute('id') || ''

    this.isEditing = this.id
  }

  handleSubmit(e) {
    e.preventDefault()

    const formData = new FormData(e.target)
    const data = Object.fromEntries(formData)

    fetch("/animais", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    }).then(response => response.json())
    .then(data => console.log(data))
  }

  connectedCallback() {

    this.shadowRoot.innerHTML = `
        <form method="post" action="/animais" id="form">
        <img th:src="@{/img/bennys-logo.webp}" width="65" alt="Logo Tio Benny's" />
        <h1 class="text-2xl font-bold">Ficha de animal</h1>
        <div class="w-full">
          <label for="nome" class="text-sm font-bold">Nome</label>
          <input type="text" name="nome" placeholder="Nome do animal" value="${this.nome}"
            class="p-2 bg-purple-100 rounded-lg w-full" required>
        </div>
        <div class="w-full">
          <label for="dono" class="text-sm font-bold">Dono</label>
          <input type="text" name="dono" placeholder="Nome do dono do animal" value="${this.dono}"
            class="p-2 bg-purple-100 rounded-lg w-full" required>
        </div>
        <div class="w-full">
          <label for="telefone" class="text-sm font-bold">Espécie</label>
          <select name="especie" class="p-2 bg-purple-100 rounded-lg w-full" required>
            <option value="Selecione">Selecione uma opção</option>
            <option value="Cachorro" selected="${this.especie == 'Cachorro'}">Cachorro</option>
            <option value="Gato" th:selected="${this.especie == 'Gato'}">Gato</option>
          </select>
        </div>
        <input type="submit" value="Cadastrar" class="p-2 rounded-lg bg-purple-300 text-purple-900 font-bold w-full">
      </form>
        `

    this.shadowRoot.querySelector('#form').addEventListener('submit', this.handleSubmit)
  }

}

export default AnimalForm;