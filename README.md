# 🌤️ WeatherApp

<div align="center">
  <strong>Aplicativo Android em Kotlin e Jetpack Compose para exibir previsão do tempo baseado na sua localização em tempo real!</strong>
</div>

<div align="center">
  <img src="https://img.shields.io/badge/Kotlin-yellow?style=for-the-badge&logo=kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-blue?style=for-the-badge&logo=jetpack%20compose"/>
  <img src="https://img.shields.io/badge/Retrofit-orange?style=for-the-badge&logo=retrofit"/>
  <img src="https://img.shields.io/badge/Gson-red?style=for-the-badge&logo=google%20apis"/>
  <img src="https://img.shields.io/badge/Coil-purple?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Hilt-lightgrey?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Play%20Services%20Location-blue?style=for-the-badge"/>
</div>

---

## 📖 Índice

- [🔍 Visão Geral](#-visão-geral)
- [📱 Funcionalidades](#-funcionalidades)
- [📐 Estrutura do Projeto](#-estrutura-do-projeto)
- [🛠️ Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [🔑 Configuração de API Key](#-configuração-de-api-key)
- [🚀 Como Executar](#-como-executar)
- [🤝 Contribuição](#-contribuição)
- [📄 Licença](#-licença)

---

## 🔍 Visão Geral

O **WeatherApp** é um aplicativo Android que, usando **Jetpack Compose**, exibe a previsão do tempo atual e previsão de 5 dias, com base na localização do usuário. Ele detecta automaticamente a conexão de rede, solicita permissão de localização, obtém coordenadas via **FusedLocationProvider** e consome a API **OpenWeatherMap**.

---

## 📱 Funcionalidades

- 📶 **Detecção de Conectividade**: exibe estado Online/Offline.  
- 📍 **Permissão e localização**: solicita ACCESS_FINE_LOCATION e busca coordenadas com alta precisão.  
- ☁️ **Previsão Atual**: mostra temperatura, descrição, umidade, pressão e visibilidade.  
- 📅 **Previsão de 5 dias**: exibe lista horizontal com ícones e temperaturas.  
- 🔄 **Refresh Manual**: botão para recarregar dados.  
- 🎨 **Design Material 3**: UI responsiva com efeitos de fundo borrado.  
- 🖼️ **Ícones**: carregamento de ícones do tempo usando **Coil**.  
- 🔧 **Arquitetura MVVM**: separação de responsabilidades.  
- 🔌 **Injeção de Dependências**: usando **Hilt**.  
- 🔄 **Chamadas Assíncronas**: usando **Kotlin Coroutines** e **Retrofit**.

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin**  
- **Jetpack Compose (Material3)**  
- **Hilt**  
- **Retrofit + Gson Converter**  
- **Coil**  
- **Google Play Services – Location**  
- **Kotlin Coroutines**  

---

## 🔑 Configuração de API Key

Por padrão, a `appid` (API Key) está hardcoded no `WeatherHomeViewModel`:

```kotlin
private suspend fun getCurrentData(): CurrentWeather {
    val endUrl = "weather?lat=$latitude&lon=$longitude&appid=YOUR_API_KEY"
    ...
}
```

Substitua `YOUR_API_KEY` pela sua chave da OpenWeatherMap. Registre-se em [OpenWeatherMap](https://openweathermap.org/) para obter sua API Key.

---

## 🚀 Como Executar

1. **Clone o repositório**  
   ```bash
   https://github.com/joaosehn2406/app-weather.git
   cd WeatherApp
   ```  
2. **Abra no Android Studio** e sincronize o Gradle.  
3. **Forneça sua API Key** conforme seção acima.  
4. **Execute** em emulador ou dispositivo.

---

## 🤝 Contribuição

1. Faça um fork  
2. Crie uma branch (`git checkout -b feature/nova-feature`)  
3. Commit suas mudanças (`git commit -m 'feat: descrição'`)  
4. Push (`git push origin feature/nova-feature`)  
5. Abra um Pull Request  

---

## 📄 Licença

Este projeto está sob a **MIT License**. Consulte [LICENSE](LICENSE.md) para mais detalhes.
