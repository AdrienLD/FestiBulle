'use client'
import { useRouter } from 'next/navigation'
import React from 'react'
import Filtres, { Filtre } from './filtres'
import Soiree from './soiree'
import Header from './header'
import { filtrerSoirees, getSoireesList } from './API'
import CardSoiree, { CardSoireeInterface } from './cardSoiree'

export default function Home() {
  const router = useRouter()

  const [ isFilter, setIsFilter ] = React.useState(false)
  const [ account, setAccount ] = React.useState('')
  const [ soirees, setSoirees ] = React.useState<CardSoireeInterface[]>([])
  const [ filtre, setFiltre ] = React.useState<Filtre|null>(null)

  React.useEffect(() => {
    const fetchData = async () => {
      const rechercheFiltree = await filtrerSoirees(filtre)
      setSoirees(rechercheFiltree)
    }
    fetchData();
  }, [ filtre ])

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
        {isFilter && <Filtres setFiltre={setFiltre} />}
        <Soiree/>
      </header>
      <div className="soireescarte grid grid-cols-2  md:grid-cols-3 lg:grid-cols-4 gap-4 mx-5 mt-5">
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
