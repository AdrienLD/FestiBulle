'use client'
import React from 'react'
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle
} from '@/components/ui/card'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Checkbox } from '@/components/ui/checkbox'
import { Label } from '@/components/ui/label'
import { connectionUser, registerUser } from '../API'
import { useRouter } from 'next/navigation'

const LoginPage: React.FC = () => {
  const [ nom, setNom ] = React.useState('')
  const [ mail, setMail ] = React.useState('')
  const [ password, setPassword ] = React.useState('')
  const [ isEighteenOrOlder, setIsEighteenOrOlder ] = React.useState(false)

  const router = useRouter()

  const inscription = async () => {
    if (!isEighteenOrOlder) {
      alert('Vous devez avoir plus de 18 ans pour vous inscrire')
      return
    } else {
      console.log('Inscription')
      const register = await registerUser(nom, mail, password)
      console.log(register.token)
      localStorage.setItem('token', register.token)
      router.push('/')
    } 
  }

  const connection = async () => {
    const register = await connectionUser(mail, password)
    console.log(register.token)
    localStorage.setItem('token', register.token)
    router.push('/')

  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <Card className="w-full max-w-md p-4 bg-white rounded-lg shadow-md">
        <CardHeader>
          <CardTitle className="text-lg font-semibold text-center text-gray-700">Connexion</CardTitle>
        </CardHeader>
        <CardContent>
          <Input type="text" placeholder="Nom" className="mb-4" value={nom} onChange={(e) => setNom(e.target.value)} />
          <Input type="email" placeholder="E-mail" className="mb-4" value={mail} onChange={(e) => setMail(e.target.value)} />
          <Input type="password" placeholder="Mot de Passe" className="mb-4" value={password} onChange={(e) => setPassword(e.target.value)} />
          <div className="flex items-center mb-4">
            <Checkbox id="terms" checked={isEighteenOrOlder} onCheckedChange={(checked: boolean) => setIsEighteenOrOlder(checked)}/>
            <Label htmlFor="terms" className="ml-2 text-sm text-gray-600">Je confirme avoir plus de 18 ans</Label>
          </div>
        </CardContent>
        <CardFooter className="flex flex-col space-y-2">
          <Button variant="secondary" className="w-full" onClick={inscription}>S'inscrire</Button>
          <Button className="w-full" onClick={connection}>Se connecter</Button>
        </CardFooter>
      </Card>
    </div>
  )
}

export default LoginPage
