'use client'
import { useRouter } from 'next/navigation'
import React from 'react'
import Filtres from './filtres'
import Soiree from './soiree'

export default function Home() {
  const router = useRouter()

  React.useEffect(() => {
    const account = localStorage.getItem('account')
    if (!account) {
      router.push('/login')
    } else {
      console.log('Account:', account)
    }
  }, [])

  return (
    <>
      <div className="flex">
        <Filtres />
        <Soiree />
      </div>
    </>
  )
}
