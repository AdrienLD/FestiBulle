import React from 'react'
import Image from 'next/image'
import { Button } from '@/components/ui/button'
import { connectedUser } from './API'

interface HeaderProps {
  isFilter: boolean
  setIsFilter: (isFilter: boolean) => void
  token: string
}
  
const Header: React.FC<HeaderProps> = ({ isFilter, setIsFilter, token }) => {

  React.useEffect(() => {
    const fetchData = async () => {
      const user = await connectedUser(token)
      console.log(user)
    }
    if (token) {
      fetchData()
    }
  }, [token])

  return (
    <div className='w-full flex  bg-card p-4 rounded-lg shadow-lg w-full'>
      <Image src="/logo.png" alt="logo" width={300} height={200} />
      <Button onClick={() => setIsFilter(!isFilter)}>Afficher les filtres</Button>
      <div className="compte">

      </div>
    </div>
  )
}

export default Header