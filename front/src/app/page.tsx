'use client'
import { useRouter } from 'next/navigation'
import React from 'react'
import Filtres from './filtres'
import Soiree from './soiree'
import Header from './header'

export default function Home() {
  const router = useRouter()

  const [ isFilter, setIsFilter ] = React.useState(false)
  const [ account, setAccount ] = React.useState('')

  React.useEffect(() => {
    const account = localStorage.getItem('token')
    if (!account) {
      router.push('/login')
    } else {
      setAccount(account)
    }
  }, [])

  return (
    <>
      <header className="flex flex-col items-center sticky">
        <Header isFilter={isFilter} setIsFilter={setIsFilter} token={account}/>
        {isFilter && <Filtres />}
        <Soiree/>
      </header>
    </>
  )
}
