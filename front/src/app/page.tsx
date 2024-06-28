'use client'
import { useRouter } from 'next/navigation'
import React from 'react'
import Filtres from './filtres'
import Soiree from './soiree'
import Header from './header'
import { getSoireesList } from './API'
import CardSoiree, { CardSoireeInterface } from './cardSoiree'

export default function Home() {
  const router = useRouter()

  const [ isFilter, setIsFilter ] = React.useState(false)
  const [ account, setAccount ] = React.useState('')
  const [ soirees, setSoirees ] = React.useState<CardSoireeInterface[]>([])

  const fetchData = async () => {
    const account = localStorage.getItem('token')
    if (!account) {
      router.push('/login')
    } else {
      setAccount(account)
      const soireesList= await getSoireesList()
      setSoirees(soireesList)
    }
  }
  
  React.useEffect(() => {
    fetchData()
  }, [])

  return (
    <>
      <header className="flex flex-col items-center  sticky top-0 z-10 bg-white">
        <Header isFilter={isFilter} setIsFilter={setIsFilter} token={account}/>
        {isFilter && <Filtres />}
        <Soiree/>
      </header>
      <div className="soireescarte gap">
        {soirees
      && soirees.map((soiree) => (
        <div key={soiree.id}>
          <CardSoiree soiree={soiree} />
        </div>
      ))
        }
      </div>
      
    </>
  )
}
