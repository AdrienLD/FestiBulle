export async function registerUser(nom: string, email: string, motDePasse: string) {
  const url = 'http://localhost:8081/api/auth/register'

  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        nom, 
        email,
        motDePasse
      })
    })

    if (response.ok) {
      const data = await response.json()
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function connectionUser(email: string, motDePasse: string) {
  const url = 'http://localhost:8081/api/auth/login'

  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email,
        motDePasse
      })
    })

    if (response.ok) {
      const data = await response.json()
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function connectedUser(token: string) {
  const url = 'http://localhost:8081/api/v1/utilisateurs/profile'
  
  try {
    console.log('token', token)
    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      }
    })
  
    if (response.ok) {
      const data = await response.json()
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function createSoiree(body: any) {
  const url = 'http://localhost:8081/api/soirees'
      
  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(body)
    })
      
    if (response.ok) {
      const data = await response.json()
      console.log('Registration successful:', data)
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function getSoireesList() {
  const url = 'http://localhost:8081/api/soirees'
    
  try {
    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    
    if (response.ok) {
      const data = await response.json()
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function getAdresse(id: number) {
  const url = `http://localhost:8081/api/v1/adresses/${id}`
      
  try {
    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      
    if (response.ok) {
      const data = await response.json()
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function createAdresse(body: any) {
  const url = `http://localhost:8081/api/v1/adresses`
      
  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(body)
    })
      
    if (response.ok) {
      const data = await response.json()
      console.log('Registration successful:', data)
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function getUser(id: number) {
  const url = `http://localhost:8081/api/v1/utilisateurs/${id}`
      
  try {
    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      
    if (response.ok) {
      const data = await response.json()
      console.log('Registration successful:', data)
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}

export async function filtrerSoirees(filtre:any) {
  const url = `http://localhost:8081/api/soirees/recherche`
      
  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
        body: JSON.stringify(filtre)
    })
      
    if (response.ok) {
      const data = await response.json()
      console.log('Registration successful:', data)
      return data
    } else {
      const errorData = await response.json()
      console.error('Registration failed:', errorData)
    }
  } catch (error) {
    console.error('Network error:', error)
  }
}