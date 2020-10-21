class WorldVector{
public:
    WorldVector(const WorldVector&) = delete;
    static WorldVector& Get(){
        return instance;
    }

    static void print() {
        std::cout << "A WorldVector Class.\n"
            <<"x = "<<x<<"\ny = "<<y<<"\nz = "<<z<<"\n";
    }
private:
    static const int x = 0, y = 0, z = 0;
    //if not const gotta be initialized outside of class(static after all)
    WorldVector() {}
    static WorldVector instance;
};


//intitalize in a .cpp file(with static variables if not const)
WorldVector WorldVector::instance;
