<template>
  <div class="min-vh-100 py-5" style="background-color: #eef4ff;">
    <div class="container" style="max-width: 860px;">
      <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
          <h1 class="h3 fw-bold mb-1">Minha conta</h1>
          <p class="text-muted mb-0">Informações da sua conta no AuthLuiz</p>
        </div>
        <button class="btn btn-outline-danger" @click="sair">Sair</button>
      </div>

      <div v-if="carregando" class="alert alert-info">Carregando conta...</div>
      <div v-else-if="erro" class="alert alert-danger">{{ erro }}</div>

      <div v-else-if="conta" class="card shadow border-0 rounded-4">
        <div class="card-body p-4">
          <div class="row g-4">
            <div class="col-md-6">
              <h2 class="h5 mb-3">Identidade</h2>
              <p><strong>Nome:</strong> {{ conta.nome }}</p>
              <p><strong>E-mail:</strong> {{ conta.email }}</p>
            </div>
            <div class="col-md-6">
              <h2 class="h5 mb-3">Metadados da conta</h2>
              <p><strong>ID do usuário:</strong> {{ conta.idUsuario }}</p>
              <p><strong>Criada em:</strong> {{ formatarDataHora(conta.dataCriacao) }}</p>
              <p><strong>Atualizada em:</strong> {{ formatarDataHora(conta.dataAtualiza) }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { buscarMinhaConta, logout } from '../services/autenticacaoService'
import { extrairMensagemErro } from '../utils/extrairMensagemErro'

const router = useRouter()
const conta = ref(null)
const carregando = ref(true)
const erro = ref('')

function formatarDataHora(data) {
  if (!data) return 'Não informado'
  return new Date(data).toLocaleString('pt-BR')
}

function sair() {
  logout()
  router.push('/login')
}

async function carregarConta() {
  carregando.value = true
  erro.value = ''
  try {
    conta.value = await buscarMinhaConta()
  } catch (e) {
    erro.value = extrairMensagemErro(e, 'Não foi possível carregar a conta.')
    console.error(e)
  } finally {
    carregando.value = false
  }
}

onMounted(carregarConta)
</script>
