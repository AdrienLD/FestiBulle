export async function registerUser(nom: string, email: string, motDePasse: string) {
    const url = 'http://localhost:8081/api/auth/register';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nom, 
                email,
                motDePasse,
            }),
        });

        if (response.ok) {
            const data = await response.json();
            console.log('Registration successful:', data);
            return data;
        } else {
            const errorData = await response.json();
            console.error('Registration failed:', errorData);
        }
    } catch (error) {
        console.error('Network error:', error);
    }
}

export async function inscriptionUser(nom: string, email: string, motDePasse: string) {
    const url = 'http://localhost:8081/api/auth/register';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nom, 
                email,
                motDePasse,
            }),
        });

        if (response.ok) {
            const data = await response.json();
            console.log('Registration successful:', data);
            return data;
        } else {
            const errorData = await response.json();
            console.error('Registration failed:', errorData);
        }
    } catch (error) {
        console.error('Network error:', error);
    }
}